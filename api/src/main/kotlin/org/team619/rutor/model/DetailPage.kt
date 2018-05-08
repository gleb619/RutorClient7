package org.team619.rutor.model

import org.team619.rutor.core.Page
import java.util.*

/**
 * Created by BORIS on 07.08.2016.
 */
data class DetailPage(
        val id: String = ""
        , val name: String = ""
        , val fileName: String = ""
        , val link: String = ""
        , val body: String = ""
        , val details: String = ""
        , val bounded: List<Row> = ArrayList()
        , val comments: List<Comment> = ArrayList()) : Page {

    override fun toString(): String {
        return "DetailPage(id='$id', name='$name', fileName='$fileName', link='$link', details='$details', bounded=$bounded, comments=$comments)"
    }

}
