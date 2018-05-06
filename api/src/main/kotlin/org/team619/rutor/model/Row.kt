package org.team619.rutor.model

import org.team619.rutor.core.DefaultEntity

/**
 * Created by BORIS on 07.08.2016.
 */
data class Row(var id: Int = -1
               , var creationDate: String = ""
               , var caption: Caption = Caption.empty()
               , var size: String = ""
               , var seeds: Int = 0
               , var peers: Int = 0
               , var comments: Int = 0
               , var detailUrl: String = ""
               , var downloadUrl: String = ""
               , var magnetUrl: String = ""
               , var fileName: String = "") : DefaultEntity
