package org.lyrebird.spaceexplorer.domain.model.launch

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Failures (

    @SerialName("time"     ) var time     : Int?    = null,
    @SerialName("altitude" ) var altitude : Int? = null,
    @SerialName("reason"   ) var reason   : String? = null

)