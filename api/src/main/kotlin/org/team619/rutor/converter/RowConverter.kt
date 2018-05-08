package org.team619.rutor.converter

import org.jsoup.nodes.Element
import org.team619.rutor.core.Logger
import org.team619.rutor.model.Row
import org.team619.rutor.util.Util

/**
 * Created by BORIS on 27.08.2016.
 */
abstract class RowConverter(logger: Logger) : DefaultConverter(logger) {

    protected fun parseRow(torrentRow: List<Element>): Row? {
        var row: Row? = null

        if (torrentRow.size >= Constants.SIZE_STANDARD) {
            row = Row()
            var torrentRowIndex = parseRowUrls(torrentRow, row)

            if (torrentRow.size == Constants.SIZE_STANDARD) {
                row.size = Util.parseString(torrentRow[torrentRowIndex++].text())
            } else {
                if (torrentRow.size >= Constants.SIZE_WITH_COMMENT) {
                    row.comments = parseInteger(torrentRow[torrentRowIndex++].text())
                    row.size = Util.parseString(torrentRow[torrentRowIndex++].text())
                }
            }

            val downloadInfoDetails = torrentRow[torrentRowIndex++].getElementsByTag(Constants.SPAN)
            if (downloadInfoDetails.size >= Constants.SIZE_HAS_TOPICS) {
                var downloadInfoDetailsIndex = 0
                row.seeds = parseInteger(downloadInfoDetails[downloadInfoDetailsIndex++].text())
                row.peers = parseInteger(downloadInfoDetails[downloadInfoDetailsIndex++].text())
            }
        }

        return row
    }

    protected fun parseRowUrls(torrentRow: List<Element>, row: Row): Int {
        var torrentRowIndex = 0
        row.creationDate = torrentRow[torrentRowIndex++].text()
        val captionDetails = torrentRow[torrentRowIndex++].getElementsByTag(Constants.LINK)
        if (captionDetails.size >= Constants.SIZE_CAPTION) {
            var captionDetailsIndex = 0

            row.downloadUrl = captionDetails[captionDetailsIndex++].attr(Constants.HREF)
            row.id = Util.parseId(row.downloadUrl)
            row.magnetUrl = captionDetails[captionDetailsIndex++].attr(Constants.HREF)
            row.detailUrl = captionDetails[captionDetailsIndex].attr(Constants.HREF)
            row.fileName = parseTorrentName(captionDetails[captionDetailsIndex].attr(Constants.HREF))
            row.caption = parseCaption(captionDetails[captionDetailsIndex++].text())
        }

        return torrentRowIndex
    }

    interface Constants {

        companion object {

            const val SIZE_STANDARD = 4
            const val SIZE_WITH_COMMENT = 5
            const val SIZE_HAS_TOPICS = 2
            const val SIZE_CAPTION = 3

            const val LINK = "a"
            const val HREF = "href"
            const val SPAN = "span"
        }

    }

}
