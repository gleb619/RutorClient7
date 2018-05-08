package org.team619.rutor.core

interface Logger {

    fun error(message: String)

    fun error(e: Exception, message: String)

    fun error(e: Exception, message: String, vararg args: String)

}
