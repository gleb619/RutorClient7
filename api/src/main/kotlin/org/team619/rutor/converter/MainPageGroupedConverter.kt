package org.team619.rutor.converter

import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.team619.rutor.core.Converter
import org.team619.rutor.core.Logger
import org.team619.rutor.model.Group
import org.team619.rutor.model.MainGroupedPage
import org.team619.rutor.util.Constants.Companion.GROUP
import org.team619.rutor.util.Constants.Companion.GROUP_ROWS
import org.team619.rutor.util.Constants.Companion.TD
import org.team619.rutor.util.Util
import java.util.*

/**
 * Created by BORIS on 07.08.2016.
 */
class MainPageGroupedConverter(logger: Logger) : RowConverter(logger), Converter<MainGroupedPage, Document> {

    override fun convert(input: Document): MainGroupedPage {
        val elements = input.select(GROUP)
                .first()
                .children()
                .drop(1)
        val rawGroups = Util.batch(elements, 2)
        val groups = rawGroups
                .map { group -> convertToGroup(group) }

        return MainGroupedPage(groups)
    }

    private fun convertToGroup(group: List<Element>): Group {
        var index = 0
        val header = group[index++]
        val topics = group[index++]
        val rows = topics.select(GROUP_ROWS).drop(1)
                .map { tr ->
                    parseRow(
                            tr.getElementsByTag(TD))
                }
                .filter({ Objects.nonNull(it) })
                .map { it!! }

        return Group(header.text(), rows)
    }

}
