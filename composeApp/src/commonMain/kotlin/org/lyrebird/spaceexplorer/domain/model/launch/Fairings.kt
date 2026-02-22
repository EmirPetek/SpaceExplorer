package org.lyrebird.spaceexplorer.domain.model.launch

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
data class Fairings (

    @SerialName("reused"           ) var reused          : Boolean?          = null,
    @SerialName("recovery_attempt" ) var recoveryAttempt : Boolean?          = null,
    @SerialName("recovered"        ) var recovered       : Boolean?          = null,
    @SerialName("ships"            ) var ships           : ArrayList<String> = arrayListOf()

)