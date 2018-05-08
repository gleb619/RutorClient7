package org.team619.rutor.model

import org.jsoup.nodes.Element
import org.jsoup.parser.Tag

/**
 * Created by BORIS on 27.08.2016.
 */
class BlankElement : Element {

    constructor() : super(Tag.valueOf("br"), "")

    override fun remove() {

    }

    override fun attr(attributeKey: String): String {
        return ""
    }

    override fun toString(): String {
        return ""
    }

    companion object {

        fun newOne(): BlankElement {
            return BlankElement()
        }
    }

}
