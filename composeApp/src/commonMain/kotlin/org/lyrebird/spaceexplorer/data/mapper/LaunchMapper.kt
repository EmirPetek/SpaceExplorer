package org.lyrebird.spaceexplorer.data.mapper

import org.lyrebird.spaceexplorer.core.util.formatLaunchDate
import org.lyrebird.spaceexplorer.domain.model.launch.Launch
import org.lyrebird.spaceexplorer.domain.model.rocket.Rocket
import org.lyrebird.spaceexplorer.domain.model.uiModel.LaunchUIModel

fun mapToUIModel(launch: Launch, rocket: Rocket?): LaunchUIModel {
    return LaunchUIModel(
        id = launch.id ?: "",
        rocketId = launch.rocket ?: "",
        missionName = launch.name ?: "Unknown Mission",
        dateText = formatLaunchDate(launch.dateUtc, isTime = false),
        dateTimeText = formatLaunchDate(launch.dateUtc, isTime = true),
        isSuccess = launch.success,
        rocketName = rocket?.name ?: "Unknown Rocket",
        rocketDescription = rocket?.description,
        patchUrl = launch.links?.patch?.large ?: launch.links?.patch?.small,
        heroImageUrl = launch.links?.flickr?.original?.takeIf { it.isNotEmpty() } ?: rocket?.flickrImages,
        launchDetails = launch.details,
        videoUrl = launch.links?.webcast,
        articleUrl = launch.links?.article,
        height = rocket?.height?.meters?.let { "${it}m" },
        mass = rocket?.mass?.kg?.let { "${it}kg" },
        wikipediaUrl = rocket?.wikipedia
    )
}