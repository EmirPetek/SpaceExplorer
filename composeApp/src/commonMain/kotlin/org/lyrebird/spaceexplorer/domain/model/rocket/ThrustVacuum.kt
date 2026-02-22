package org.lyrebird.spaceexplorer.domain.model.rocket

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
data class ThrustVacuum (

    @SerialName("kN"  ) var kN  : Int? = null,
    @SerialName("lbf" ) var lbf : Int? = null

)