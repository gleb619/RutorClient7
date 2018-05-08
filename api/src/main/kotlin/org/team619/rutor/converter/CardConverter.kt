package org.team619.rutor.converter

import org.jsoup.select.Elements
import org.team619.rutor.core.Converter
import org.team619.rutor.core.Logger
import org.team619.rutor.model.Row
import java.util.*

/**
 * Created by BORIS on 27.08.2016.
 */
class CardConverter(logger: Logger) : RowConverter(logger), Converter<List<Row>, Elements> {

    override fun convert(rows: Elements): List<Row> {
        return rows.drop(1)
                .map { element ->
                    parseRow(
                            element.getElementsByTag("td"))
                }
                .filter { row -> Objects.nonNull(row) }
                .map { it!! }
    }

}
