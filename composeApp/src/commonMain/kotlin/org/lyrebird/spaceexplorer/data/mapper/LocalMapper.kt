package org.lyrebird.spaceexplorer.data.mapper

import org.lyrebird.spaceexplorer.core.util.formatLaunchDate
import org.lyrebird.spaceexplorer.data.local.entity.launch.LaunchEntity
import org.lyrebird.spaceexplorer.data.local.entity.rocket.RocketEntity
import org.lyrebird.spaceexplorer.domain.model.launch.Flickr
import org.lyrebird.spaceexplorer.domain.model.launch.Launch
import org.lyrebird.spaceexplorer.domain.model.launch.Links
import org.lyrebird.spaceexplorer.domain.model.launch.Patch
import org.lyrebird.spaceexplorer.domain.model.rocket.Height
import org.lyrebird.spaceexplorer.domain.model.rocket.Mass
import org.lyrebird.spaceexplorer.domain.model.rocket.Rocket
import org.lyrebird.spaceexplorer.domain.model.uiModel.LaunchUIModel

fun Launch.toEntity(): LaunchEntity {
    return LaunchEntity(
        id = this.id ?: "",
        rocketId = this.rocket ?: "",
        missionName = this.name ?: "Unknown Mission",
        dateUtc = this.dateUtc,
        isSuccess = this.success,
        launchDetails = this.details,
        patchUrl = this.links?.patch?.large ?: this.links?.patch?.small,
        flickrImages = this.links?.flickr?.original,
        videoUrl = this.links?.webcast,
        articleUrl = this.links?.article
    )
}

fun Rocket.toEntity(): RocketEntity {
    return RocketEntity(
        id = this.id ?: "",
        name = this.name ?: "Unknown Rocket",
        description = this.description,
        heightMeters = this.height?.meters,
        massKg = this.mass?.kg,
        wikipediaUrl = this.wikipedia,
        flickrImages = this.flickrImages
    )
}

fun LaunchEntity.toDomainModel(): Launch {
    return Launch(
        id = this.id,
        name = this.missionName,
        dateUtc = this.dateUtc,
        rocket = this.rocketId,
        success = this.isSuccess,
        details = this.launchDetails,
        links = Links(
            patch = Patch(small = this.patchUrl, large = this.patchUrl),
            flickr = Flickr(
                original = ArrayList(
                    this.flickrImages ?: emptyList()
                )
            ),
            webcast = this.videoUrl,
            article = this.articleUrl
        )
    )
}

fun RocketEntity.toDomainModel(): Rocket {
    return Rocket(
        id = this.id,
        name = this.name,
        description = this.description,
        height = Height(meters = this.heightMeters),
        mass = Mass(kg = this.massKg),
        wikipedia = this.wikipediaUrl,
        flickrImages = ArrayList(this.flickrImages ?: emptyList())
    )
}
