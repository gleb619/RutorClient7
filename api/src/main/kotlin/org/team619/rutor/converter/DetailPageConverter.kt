package org.team619.rutor.converter

import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.select.Elements
import org.team619.rutor.core.Converter
import org.team619.rutor.core.Logger
import org.team619.rutor.model.BlankElement
import org.team619.rutor.model.BlankElements
import org.team619.rutor.model.DetailPage
import org.team619.rutor.util.Util
import java.util.*

/**
 * Created by BORIS on 07.08.2016.
 */
class DetailPageConverter(
        logger: Logger,
        private val cardConverter: CardConverter,
        private val commentConverter: CommentConverter) :
        DefaultConverter(logger), Converter<DetailPage, Document> {

    private fun parseIdentifier(document: Document): String {
        var id = ""
        if (document.location() != null && document.location().isNotEmpty()) {
            id = document.location()
        }

        if (document.title() != null && document.title().isNotEmpty()) {
            id = id + document.title().hashCode() + ""
        } else {
            id = ""
        }

        return parseFileName(id)
    }

    private fun parseElement(element: Element): Element {
        return Optional.ofNullable(element).orElse(BlankElement.newOne())
    }

    private fun parseElement(element: Elements): Elements {
        return Optional.ofNullable(element).orElse(BlankElements.newList())
    }

    private fun parseFileName(location: String): String {
        return StringBuilder()
                .append(location.replace("[^A-z0-9]+".toRegex(), "")
                        .replace("https|http".toRegex(), "")
                )
                .append(Selectors.EXTENSION)
                .toString()
    }

    override fun convert(input: Document): DetailPage {
        val fileName = parseIdentifier(input)
        return parseContent(input, createLink(fileName))
    }

    private fun createLink(fileName: String): String {
        return StringBuilder(Selectors.PROTOCOL_HTTP)
                .append("localhost")
                .append(Selectors.COLON)
                .append("8080")
                .append(Selectors.BACKSLASH)
                .append(fileName)
                .toString()
    }

    private fun parseContent(document: Document, link: String): DetailPage {
        val htmlBody = document.body()
        val name = htmlBody.select(Selectors.HEADER).text()
        val mainContent = htmlBody.select(Selectors.CONTENT).first()

        val mainLink = parseElement(htmlBody.select(Selectors.TOPIC_ID).first())
        val id = Integer.toString(Util.parseId(mainLink.attr("href").toString()))
        val selfLink = mainLink.attr("href")
        val table = mainContent.select(Selectors.MAIN_TABLE).first()

        //999937765 = Залил
        if (table.getElementsByTag("tr")[1].getElementsByTag("td")[0].text().hashCode() != 999937765) {
            table.select("tr:nth-child(1)").after("" +
                    "<tr>" +
                    "<td class='header'>Залил</td>" +
                    "<td><b><a href='/browse/0/0/0/0' target='_blank'>Unknown</a></b></td>" +
                    "</tr>")
        }

        var topicSpecification = parseElement(table.select(Selectors.TOPIC_DETAILS).first()).toString()
        val boundedContentElement = parseElement(table.select(Selectors.TOPIC_BOUNDED).first())
        val commentsContentElement = parseElement(mainContent.select(Selectors.TOPIC_COMMENTS))

        parseElement(table.select(Selectors.TOPIC_FILE).first()).remove()
        parseElement(table.select(Selectors.TOPIC_BOUNDED).first()).remove()
        parseElement(table.getElementsByTag(Selectors.TR).first()).remove()

        val distributionDetails = parseElement(table.getElementsByTag(Selectors.TR)).toString()
        val boundedContent = cardConverter.convert(boundedContentElement.select(Selectors.TOPIC_BOUNDED_DETAILS))
        val commentsContent = commentConverter.convert(commentsContentElement)

        topicSpecification = topicSpecification.replace("hidehead", "hidehead plus")
        //        commentsContent = commentsContent.replace("hidehead", "hidehead plus");

        return DetailPage(id = id
                , name = name
                , fileName = link
                , link = selfLink
                , body = topicSpecification
                , details = distributionDetails
                , bounded = boundedContent
                , comments = commentsContent)
    }

    interface Selectors {

        companion object {

            const val HEADER = "#all > h1"
            const val CONTENT = "#content"
            const val MAIN_TABLE = "#details > tbody"
            const val TR = "tr"
            const val TOPIC_ID = "#download > a:nth-child(2)"
            const val TOPIC_DETAILS = "tr:nth-child(1) > td:nth-child(2)"
            const val TOPIC_BOUNDED = "tr:nth-child(11)"
            const val TOPIC_BOUNDED_DETAILS = "#index > fieldset > table > tbody > tr"
            const val TOPIC_FILE = "tr:nth-child(12)"
            const val TOPIC_COMMENTS = "#content > table:nth-child(16) > tbody > tr"

            const val COLON = ":"
            const val BACKSLASH = "/"
            const val PROTOCOL_HTTP = "http://"
            const val EXTENSION = ".html"
        }

    }

    override fun support(element: Element): Boolean {
        val text = !(element.select("#all > h1")?.text()?.isNullOrEmpty() ?: true)
        val link = !(element.select("#download > a:nth-child(2)")?.attr("href")?.isNullOrEmpty() ?: true)

        return text && link
    }

}
