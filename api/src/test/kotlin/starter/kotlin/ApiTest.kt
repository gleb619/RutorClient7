package starter.kotlin

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.fail
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.junit.Before
import org.junit.Test
import org.team619.rutor.converter.*
import org.team619.rutor.core.Logger
import org.team619.rutor.model.DetailPage
import org.team619.rutor.model.Row
import java.nio.file.Files
import java.nio.file.Paths

class ApiTest {

    lateinit var logger: Logger
    lateinit var docPlain: Document
    lateinit var docGrouped: Document
    lateinit var docDetail: Document
    lateinit var cardConverter: CardConverter
    lateinit var commentConverter: CommentConverter

    lateinit var awaitedRow1: Row
    lateinit var awaitedRow2: Row
    lateinit var awaitedDetailPage: DetailPage

    @Before
    fun before() {
        val plainPath = ClassLoader.getSystemResource("plain.html").toURI()
        val groupedPath = ClassLoader.getSystemResource("grouped.html").toURI()
        val detailPath = ClassLoader.getSystemResource("detail.html").toURI()
        val contentPlain = String(Files.readAllBytes(Paths.get(plainPath)))
        val contentGrouped = String(Files.readAllBytes(Paths.get(groupedPath)))
        val contentDetail = String(Files.readAllBytes(Paths.get(detailPath)))

        docPlain = Jsoup.parse(contentPlain)
        docGrouped = Jsoup.parse(contentGrouped)
        docDetail = Jsoup.parse(contentDetail)

        logger = object : Logger {
            override fun error(s: String) {
                fail(s)
            }

            override fun error(e: Exception, s: String) {
                e.printStackTrace()
                fail(s)
            }

            override fun error(e: Exception, message: String, vararg args: String) {
                e.printStackTrace()
                fail(String.format(message, *args))
            }
        }

        cardConverter = CardConverter(logger)
        commentConverter = CommentConverter(logger)
        awaitedRow1 = DataSupplier.awaitedRow1
        awaitedRow2 = DataSupplier.awaitedRow2
        awaitedDetailPage = DataSupplier.awaitedDetailPage
    }

    @Test
    fun testMainPlainPage() {
        val mainPagePlainConverter = MainPagePlainConverter(logger)
        val result = mainPagePlainConverter.convert(docPlain)
        assertThat(result)
                .isNotNull
        assertThat(result.rows)
                .hasSize(285)
                .contains(awaitedRow1)
    }

    @Test
    fun testMainGroupedPage() {
        val mainPageGroupedConverter = MainPageGroupedConverter(logger)
        val result = mainPageGroupedConverter.convert(docGrouped)
        assertThat(result.groups)
                .isNotNull
                .hasSize(15)
        assertThat(result.groups[0].name)
                .isNotBlank()
                .isEqualTo("Зарубежные фильмы")
        assertThat(result.groups[0].rows)
                .hasSize(73)
                .contains(awaitedRow2)
    }

    @Test
    fun testDetailPage() {
        val detailPageConverter = DetailPageConverter(logger, cardConverter, commentConverter)
        val result = detailPageConverter.convert(docDetail)
        assertThat(result)
                .isNotNull
                .isEqualToIgnoringGivenFields(awaitedDetailPage, "body")
        assertThat(result.body.substring(0..2550))
                .isNotBlank()
                .isEqualTo(awaitedDetailPage.body.substring(0..2550))

    }

}