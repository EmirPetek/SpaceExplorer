package org.lyrebird.spaceexplorer.domain.model.rocket


import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
data class SecondStage (

    @SerialName("thrust"           ) var thrust         : Thrust?   = Thrust(),
    @SerialName("payloads"         ) var payloads       : Payloads? = Payloads(),
    @SerialName("reusable"         ) var reusable       : Boolean?  = null,
    @SerialName("engines"          ) var engines        : Int?      = null,
    @SerialName("fuel_amount_tons" ) var fuelAmountTons : Double?   = null,
    @SerialName("burn_time_sec"    ) var burnTimeSec    : Int?      = null

)