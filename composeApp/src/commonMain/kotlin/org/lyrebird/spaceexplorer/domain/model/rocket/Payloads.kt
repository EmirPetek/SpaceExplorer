package org.lyrebird.spaceexplorer.domain.model.rocket


import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
data class Payloads (

    @SerialName("composite_fairing" ) var compositeFairing : CompositeFairing? = CompositeFairing(),
    @SerialName("option_1"          ) var option1          : String?           = null

)