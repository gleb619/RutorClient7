package org.team619.rutor.core

import java.io.InputStream

interface Serializer {

    fun to(input: Page): InputStream

}
