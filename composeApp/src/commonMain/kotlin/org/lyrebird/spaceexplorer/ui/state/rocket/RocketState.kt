package org.lyrebird.spaceexplorer.ui.state.rocket

import org.lyrebird.spaceexplorer.domain.model.rocket.Rocket

sealed class RocketState {
    object Loading : RocketState()

    data class Success(val rocket: Rocket) : RocketState()

    data class Error(val message: String) : RocketState()
}