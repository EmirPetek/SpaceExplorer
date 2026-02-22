package org.lyrebird.spaceexplorer.ui.state.launch

import org.lyrebird.spaceexplorer.domain.model.uiModel.LaunchUIModel

sealed class LaunchListState {
    object Loading : LaunchListState()

    data class Success(val launches: List<LaunchUIModel>) : LaunchListState()

    data class Error(val message: String) : LaunchListState()
}
