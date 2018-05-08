package org.team619.rutor.model

import org.team619.rutor.core.Page

/**
 * Created by BORIS on 07.08.2016.
 */
class Caption : Page {

    val adaptedName: String
    val name: String
    val subtitle: String
    val year: String
    val title: String
        get() = if (adaptedName == null) name else adaptedName + "/" + name

    constructor(name: String) {
        this.adaptedName = ""
        this.name = name
        this.subtitle = ""
        this.year = ""
    }

    constructor(title: String, year: String, subtitle: String) {
        this.adaptedName = parse(title, 0)
        this.name = parse(title, 1)
        this.subtitle = subtitle
        this.year = year
    }

    constructor(adaptedName: String, name: String, year: String, subtitle: String) {
        this.adaptedName = adaptedName
        this.name = name
        this.subtitle = subtitle
        this.year = year
    }

    private fun parse(title: String?, i: Int): String {
        return java.lang.String.valueOf((if (title != null && title.contains("/")) title.split("/".toRegex())[i] else title)?.trim())
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Caption

        if (adaptedName != other.adaptedName) return false
        if (name != other.name) return false
        if (subtitle != other.subtitle) return false
        if (year != other.year) return false

        return true
    }

    override fun hashCode(): Int {
        var result = adaptedName.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + subtitle.hashCode()
        result = 31 * result + year.hashCode()
        return result
    }

    override fun toString(): String {
        return "Caption(adaptedName=$adaptedName, name=$name, subtitle=$subtitle, year=$year)"
    }

    companion object {
        fun empty(): Caption = Caption("", "", "")
    }

}
