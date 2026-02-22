package org.lyrebird.spaceexplorer.ui.state.launch

import org.lyrebird.spaceexplorer.domain.model.uiModel.LaunchUIModel

sealed class LaunchDetailState {
    object Loading : LaunchDetailState()
    data class Success(val data: LaunchUIModel) : LaunchDetailState()
    data class Error(val message: String) : LaunchDetailState()
}