package org.lyrebird.spaceexplorer.domain.model.rocket

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class Mass (

    @SerialName("kg" ) var kg : Long? = null,
    @SerialName("lb" ) var lb : Long? = null

)