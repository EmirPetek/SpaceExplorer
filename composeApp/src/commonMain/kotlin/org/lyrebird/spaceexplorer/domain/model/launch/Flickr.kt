package org.lyrebird.spaceexplorer.domain.model.launch

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
data class Flickr (

    @SerialName("small"    ) var small    : ArrayList<String> = arrayListOf(),
    @SerialName("original" ) var original : ArrayList<String> = arrayListOf()

)