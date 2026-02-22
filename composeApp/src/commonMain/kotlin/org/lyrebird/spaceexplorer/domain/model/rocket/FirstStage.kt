package org.lyrebird.spaceexplorer.domain.model.rocket

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
data class FirstStage (

    @SerialName("thrust_sea_level" ) var thrustSeaLevel : ThrustSeaLevel? = ThrustSeaLevel(),
    @SerialName("thrust_vacuum"    ) var thrustVacuum   : ThrustVacuum?   = ThrustVacuum(),
    @SerialName("reusable"         ) var reusable       : Boolean?        = null,
    @SerialName("engines"          ) var engines        : Int?            = null,
    @SerialName("fuel_amount_tons" ) var fuelAmountTons : Double?         = null,
    @SerialName("burn_time_sec"    ) var burnTimeSec    : Int?            = null

)