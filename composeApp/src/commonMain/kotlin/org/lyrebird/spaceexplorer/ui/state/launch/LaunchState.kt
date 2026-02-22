package org.lyrebird.spaceexplorer.ui.state.launch

import org.lyrebird.spaceexplorer.domain.model.launch.Launch

sealed class LaunchState {
    object Loading : LaunchState()

    data class Success(val launch: Launch) : LaunchState()

    data class Error(val message: String) : LaunchState()
}
