package org.lyrebird.spaceexplorer.domain.model.launch

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
data class Links (

    @SerialName("patch"      ) var patch     : Patch?  = Patch(),
    @SerialName("reddit"     ) var reddit    : Reddit? = Reddit(),
    @SerialName("flickr"     ) var flickr    : Flickr? = Flickr(),
    @SerialName("presskit"   ) var presskit  : String? = null,
    @SerialName("webcast"    ) var webcast   : String? = null,
    @SerialName("youtube_id" ) var youtubeId : String? = null,
    @SerialName("article"    ) var article   : String? = null,
    @SerialName("wikipedia"  ) var wikipedia : String? = null

)