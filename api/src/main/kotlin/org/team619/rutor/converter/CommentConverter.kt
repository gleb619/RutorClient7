package org.team619.rutor.converter

import org.jsoup.nodes.Element
import org.jsoup.select.Elements
import org.team619.rutor.core.Converter
import org.team619.rutor.core.Logger
import org.team619.rutor.model.Comment
import org.team619.rutor.util.Util


/**
 * Created by BORIS on 27.08.2016.
 */
class CommentConverter(logger: Logger) : DefaultConverter(logger), Converter<List<Comment>, Elements> {

    override fun convert(rawComments: Elements): List<Comment> {
        val parts = Util.batch(rawComments, 3)

        return parseComments(parts)
    }

    private fun parseComments(comments: List<List<Element>>): List<Comment> {
        return comments
                .map { comment ->
                    var index = 0
                    val head = comment[index++]
                    val body = comment[index++]
                    val tds = head.getElementsByTag("td")
                    index = 0
                    val userName = tds[index++].text()
                    val creationDate = tds[index++].text()
                    val mark = parseInteger(tds[index++].toString()).toString()
                    val commentId = tds[index++].getElementsByTag("span").first().attr("id")
                    val text = body.getElementsByTag("td").html()

                    Comment(commentId, userName, creationDate, mark, text)
                }
    }

}
