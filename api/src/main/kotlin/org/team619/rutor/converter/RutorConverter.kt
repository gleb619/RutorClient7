package org.team619.rutor.converter

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.team619.rutor.core.Converter
import org.team619.rutor.core.Logger
import org.team619.rutor.core.Page
import org.team619.rutor.core.Serializer
import java.io.ByteArrayInputStream
import java.io.IOException
import java.io.InputStream
import java.nio.charset.StandardCharsets

class RutorConverter(
        private val logger: Logger,
        private val serializer: Serializer,
        private val baseUri: String,
        private val converters: List<Converter<out Page, Document>>) : Converter<Page?, InputStream>, Serializer {

    override fun to(input: Page): InputStream {
        return serializer.to(input)
    }

    override fun convert(inputStream: InputStream): Page? {
        var output: Page? = null
        try {
            val document = Jsoup.parse(inputStream, StandardCharsets.UTF_8.name(), baseUri)
            output = converters
                    .filter { converter -> converter.support(document) }
                    .sortedWith(compareBy({ it.priority() }))
                    .firstOrNull()?.convert(document)
        } catch (e: IOException) {
            logger.error(e, "Can't read document")
        }

        return output
    }

    fun convert(text: String): Page? {
        var output: Page? = null
        try {
            val bytes = text.toByteArray(StandardCharsets.UTF_8)
            ByteArrayInputStream(bytes).use { stream -> output = convert(stream) }
        } catch (e: Exception) {
            logger.error(e, "Can't read document")
        }

        return output
    }

}
