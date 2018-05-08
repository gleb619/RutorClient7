package org.team619.rutor.util

import org.team619.rutor.converter.DefaultConverter
import java.util.regex.Pattern

object Util {

    fun <T> batch(source: List<T>, length: Int): List<List<T>> {
        return source.withIndex()
                .groupBy { it.index / length }
                .map { it.value.map { it.value } }
    }

    fun parseString(input: String?): String {
        return (input ?: "")
                .replace(DefaultConverter.Patterns.HTML_SPACE, "")
    }

    fun parseStringClear(input: String?): String {
        return (input ?: "")
                .replace(DefaultConverter.Patterns.HTML_SPACE, "")
                .replace(("^" + DefaultConverter.Patterns.ALL_SPACES).toRegex(), "")
                .replace((DefaultConverter.Patterns.ALL_SPACES + "$").toRegex(), "")
    }

    fun parseId(input: String?): Int {
        var output = 0
        var input = (input ?: "")

        val pattern = Pattern.compile(DefaultConverter.Patterns.ID)
        val matcher = pattern.matcher(input)
        if (matcher.find()) {
            val idInString = matcher.group(1)
            if (idInString != null) {
                output = try {
                    idInString
                            .replace(DefaultConverter.Patterns.ONLY_NUMBERS.toRegex(), "")
                            .toInt()
                } catch (e: NumberFormatException) {
                    -1
                }
            }
        } else {
            throw IllegalArgumentException(
                    StringBuilder("Id couldn't be parsed from input: ")
                            .append(input)
                            .toString())
        }

        return Math.abs(output)
    }

}
