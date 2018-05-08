package org.team619.rutor.model

import org.jsoup.select.Elements

/**
 * Created by BORIS on 27.08.2016.
 */
class BlankElements : Elements() {

    override fun toString(): String {
        return ""
    }

    companion object {

        fun newList(): Elements {
            return BlankElements()
        }
    }

}
