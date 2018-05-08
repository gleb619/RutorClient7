package org.team619.rutor.converter

import org.team619.rutor.core.Logger
import org.team619.rutor.model.Caption
import org.team619.rutor.util.Util
import java.util.regex.Pattern

/**
 * Created by BORIS on 07.08.2016.
 */
open class DefaultConverter(protected var logger: Logger) {

    fun parseInteger(input: String?): Int {
        var output = 0
        var input = (input ?: "")
                .replace(Patterns.ONLY_NUMBERS.toRegex(), "")

        if (input.isNotEmpty()) {
            output = try {
                input.toInt()
            } catch (e: NumberFormatException) {
                logger.error("ERROR: " + e.message)
                -1
            }

        }

        return output
    }

    fun parseCaption(input: String?): Caption {
        var input = (input ?: "")
        return try {
            val m = Patterns.PATTERN_CAPTION.matcher(input)
            var index = 1
            if (m.find()) {
                Caption(
                        title = Util.parseStringClear(m.group(index++)),
                        year = Util.parseStringClear(m.group(index++)),
                        subtitle = Util.parseStringClear(m.group(index++))
                )
            } else {
                Caption(
                        name = Util.parseStringClear(input))
            }
        } catch (e: Exception) {
            logger.error(e, "ERROR: ")
            Caption(input)
        }
    }

    fun parseTorrentName(input: String?): String {
        var input = (input ?: "")
        return try {
            val m = Patterns.PATTERN_TORRENT_NAME.matcher(input)
            if (m.find()) {
                m.group(3)
            } else {
                Util.parseStringClear(input)
            }
        } catch (e: Exception) {
            logger.error(e, "ERROR: ")
            input
        }
    }

    interface Patterns {

        companion object {

            const val ID = "[http|https]+:..d.+rutor\\.\\w+.download.(.*)"
            const val CAPTION = "(.*)\\((.*)\\)(.*)"
            const val TORRENT_NAME = "\\/(.*)\\/(.*)\\/(.*)"
            const val ONLY_NUMBERS = "[^0-9.]"
            const val HTML_SPACE = "&nbsp;"
            const val ALL_SPACES = "\\s+"

            val PATTERN_CAPTION = Pattern.compile(CAPTION)!!
            val PATTERN_TORRENT_NAME = Pattern.compile(TORRENT_NAME)!!
        }

    }

}
