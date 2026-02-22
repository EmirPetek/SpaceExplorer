package org.lyrebird.spaceexplorer.domain.model.rocket

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
data class PayloadWeights (

    @SerialName("id"   ) var id   : String? = null,
    @SerialName("name" ) var name : String? = null,
    @SerialName("kg"   ) var kg   : Int?    = null,
    @SerialName("lb"   ) var lb   : Int?    = null

)