package org.team619.rutor.config

import org.jsoup.nodes.Document
import org.team619.rutor.converter.*
import org.team619.rutor.core.Converter
import org.team619.rutor.core.Logger
import org.team619.rutor.core.Page
import java.util.*

class ApiBuilder private constructor(private val baseUri: String) {
    private var logger: Logger? = null

    fun withLogger(logger: Logger): ApiBuilder {
        this.logger = logger
        return this
    }

    fun build(): RutorConverter {
        Objects.requireNonNull<Logger>(logger, "Logger can't be null")
        val localLogger = logger!!
        val cardConverter = CardConverter(localLogger)
        val commentConverter = CommentConverter(localLogger)
        val converters = ArrayList<Converter<out Page, Document>>()
        converters.add(MainPagePlainConverter(localLogger))
        converters.add(MainPageGroupedConverter(localLogger))
        converters.add(DetailPageConverter(localLogger, cardConverter, commentConverter))

        return RutorConverter(localLogger, baseUri, converters)
    }

    companion object {

        fun from(uri: String): ApiBuilder {
            return ApiBuilder(uri)
        }

    }

}
