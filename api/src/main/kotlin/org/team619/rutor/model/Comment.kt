package org.team619.rutor.model

import org.team619.rutor.core.Page

/**
 * Created by BORIS on 02.09.2016.
 */
data class Comment(
        val id: String,
        val author: String,
        val created: String,
        val stars: String,
        val body: String) : Page
