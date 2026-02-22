package org.lyrebird.spaceexplorer.domain.model.uiModel

data class LaunchUIModel(
    val id: String,
    val rocketId: String,
    val missionName: String,
    val dateText: String,
    val dateTimeText: String,
    val isSuccess: Boolean?,
    val rocketName: String,
    val rocketDescription: String?,
    val patchUrl: String?,
    val heroImageUrl: ArrayList<String>?,
    val launchDetails: String?,
    val videoUrl: String?,
    val articleUrl: String?,
    val height: String? = null,
    val mass: String? = null,
    val wikipediaUrl: String?
)