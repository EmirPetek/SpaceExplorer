package org.lyrebird.spaceexplorer.domain.model.launch

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
data class Reddit (

    @SerialName("campaign" ) var campaign : String? = null,
    @SerialName("launch"   ) var launch   : String? = null,
    @SerialName("media"    ) var media    : String? = null,
    @SerialName("recovery" ) var recovery : String? = null

)