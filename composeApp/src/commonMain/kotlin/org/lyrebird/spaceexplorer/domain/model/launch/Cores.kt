package org.lyrebird.spaceexplorer.domain.model.launch

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Cores (

    @SerialName("core"            ) var core           : String?  = null,
    @SerialName("flight"          ) var flight         : Int?     = null,
    @SerialName("gridfins"        ) var gridfins       : Boolean? = null,
    @SerialName("legs"            ) var legs           : Boolean? = null,
    @SerialName("reused"          ) var reused         : Boolean? = null,
    @SerialName("landing_attempt" ) var landingAttempt : Boolean? = null,
    @SerialName("landing_success" ) var landingSuccess : Boolean?  = null,
    @SerialName("landing_type"    ) var landingType    : String?  = null,
    @SerialName("landpad"         ) var landpad        : String?  = null

)