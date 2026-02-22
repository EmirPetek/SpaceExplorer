package org.lyrebird.spaceexplorer.domain.model.rocket

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Rocket(
    @SerialName("height"           ) var height         : Height?                   = Height(),
    @SerialName("diameter"         ) var diameter       : Diameter?                 = Diameter(),
    @SerialName("mass"             ) var mass           : Mass?                     = Mass(),
    @SerialName("first_stage"      ) var firstStage     : FirstStage?               = FirstStage(),
    @SerialName("second_stage"     ) var secondStage    : SecondStage?              = SecondStage(),
    @SerialName("engines"          ) var engines        : Engines?                  = Engines(),
    @SerialName("landing_legs"     ) var landingLegs    : LandingLegs?              = LandingLegs(),
    @SerialName("payload_weights"  ) var payloadWeights : ArrayList<PayloadWeights> = arrayListOf(),
    @SerialName("flickr_images"    ) var flickrImages   : ArrayList<String>         = arrayListOf(),
    @SerialName("name"             ) var name           : String?                   = null,
    @SerialName("type"             ) var type           : String?                   = null,
    @SerialName("active"           ) var active         : Boolean?                  = null,
    @SerialName("stages"           ) var stages         : Int?                      = null,
    @SerialName("boosters"         ) var boosters       : Int?                      = null,
    @SerialName("cost_per_launch"  ) var costPerLaunch  : Int?                      = null,
    @SerialName("success_rate_pct" ) var successRatePct : Int?                      = null,
    @SerialName("first_flight"     ) var firstFlight    : String?                   = null,
    @SerialName("country"          ) var country        : String?                   = null,
    @SerialName("company"          ) var company        : String?                   = null,
    @SerialName("wikipedia"        ) var wikipedia      : String?                   = null,
    @SerialName("description"      ) var description    : String?                   = null,
    @SerialName("id"               ) var id             : String?                   = null

)
