package org.lyrebird.spaceexplorer.domain.model.rocket

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
data class LandingLegs (

    @SerialName("number"   ) var number   : Int?    = null,
    @SerialName("material" ) var material : String? = null

)