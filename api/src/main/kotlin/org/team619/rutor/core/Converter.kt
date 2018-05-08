package org.team619.rutor.core

import org.jsoup.nodes.Element

/**
 * Created by BORIS on 07.08.2016.
 */
interface Converter<OUTPUT, INPUT> {

    fun convert(input: INPUT): OUTPUT

    fun support(element: Element): Boolean {
        return java.lang.Boolean.FALSE
    }

    fun priority(): Int {
        return 1
    }

}
