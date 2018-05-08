package org.team619.rutor.converter

import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.team619.rutor.core.Converter
import org.team619.rutor.core.Logger
import org.team619.rutor.model.MainPlainPage
import org.team619.rutor.model.Row
import org.team619.rutor.util.Constants.Companion.PLAIN_ROWS
import org.team619.rutor.util.Constants.Companion.TD
import java.util.*

/**
 * Created by BORIS on 07.08.2016.
 */
class MainPagePlainConverter(logger: Logger) : RowConverter(logger), Converter<MainPlainPage, Document> {

    override fun convert(input: Document): MainPlainPage {
        val rows = input.select(PLAIN_ROWS).drop(1)
                .map { element -> toRow(element) }
                .filter({ Objects.nonNull(it) })
                .map { it!! }

        return MainPlainPage(rows)
    }

    private fun toRow(element: Element): Row? {
        return parseRow(
                element.getElementsByTag(TD))
    }

    //274289764 = Plain page text
    override fun support(element: Element): Boolean {
        val text = element.select("#index > h2").last().text().hashCode()
        val size = element.select("#index > table > tbody > tr").size

        return 274289764 == text && size >= 10
    }

}
