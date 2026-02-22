package org.lyrebird.spaceexplorer.domain.model.rocket

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
data class Isp (

    @SerialName("sea_level" ) var seaLevel : Int? = null,
    @SerialName("vacuum"    ) var vacuum   : Int? = null

)