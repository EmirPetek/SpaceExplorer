package org.lyrebird.spaceexplorer.domain.model.launch

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
data class Patch (

    @SerialName("small" ) var small : String? = null,
    @SerialName("large" ) var large : String? = null

)