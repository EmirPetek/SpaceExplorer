package org.lyrebird.spaceexplorer.domain.model.rocket

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
data class CompositeFairing (

    @SerialName("height"   ) var height   : Height?   = Height(),
    @SerialName("diameter" ) var diameter : Diameter? = Diameter()

)