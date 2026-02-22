package org.lyrebird.spaceexplorer.domain.model.rocket

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
data class Height (

    @SerialName("meters" ) var meters : Double? = null,
    @SerialName("feet"   ) var feet   : Double? = null

)