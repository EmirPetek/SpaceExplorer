package org.lyrebird.spaceexplorer.domain.model.uiModel

import kotlinx.serialization.Serializable

@Serializable
data class LaunchDetail(
    val launchID: String,
    val rocketID: String
)