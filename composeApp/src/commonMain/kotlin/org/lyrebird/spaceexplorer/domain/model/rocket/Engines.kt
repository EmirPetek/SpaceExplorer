package org.lyrebird.spaceexplorer.domain.model.rocket

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
data class Engines (

    @SerialName("isp"              ) var isp            : Isp?            = Isp(),
    @SerialName("thrust_sea_level" ) var thrustSeaLevel : ThrustSeaLevel? = ThrustSeaLevel(),
    @SerialName("thrust_vacuum"    ) var thrustVacuum   : ThrustVacuum?   = ThrustVacuum(),
    @SerialName("number"           ) var number         : Int?            = null,
    @SerialName("type"             ) var type           : String?         = null,
    @SerialName("version"          ) var version        : String?         = null,
    @SerialName("layout"           ) var layout         : String?         = null,
    @SerialName("engine_loss_max"  ) var engineLossMax  : Int?            = null,
    @SerialName("propellant_1"     ) var propellant1    : String?         = null,
    @SerialName("propellant_2"     ) var propellant2    : String?         = null,
    @SerialName("thrust_to_weight" ) var thrustToWeight : Double?         = null

)