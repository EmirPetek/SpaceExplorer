package org.lyrebird.spaceexplorer.ui.viewmodel.LaunchDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import org.lyrebird.spaceexplorer.ui.state.launch.LaunchDetailState
import org.lyrebird.spaceexplorer.ui.state.launch.LaunchState
import org.lyrebird.spaceexplorer.ui.state.rocket.RocketState
import org.lyrebird.spaceexplorer.data.mapper.mapToUIModel
import org.lyrebird.spaceexplorer.domain.usecase.launch.LaunchUseCase
import org.lyrebird.spaceexplorer.domain.usecase.rocket.RocketUseCase

class LaunchDetailViewModel(
    val launchUseCase: LaunchUseCase,
    val rocketUseCase: RocketUseCase,
): ViewModel() {

    private val _uiState = MutableStateFlow<LaunchDetailState>(LaunchDetailState.Loading)
    val uiState = _uiState.asStateFlow()

    fun fetchData(launchId: String, rocketId: String) {
        viewModelScope.launch {
            _uiState.value = LaunchDetailState.Loading

            launchUseCase.getLaunchById(launchId)
                .combine(rocketUseCase.getRocketDetails(rocketId)) { lResult, rResult ->

                    if (lResult is LaunchState.Success && rResult is RocketState.Success) {

                        val uiModel = mapToUIModel(lResult.launch, rResult.rocket)
                        LaunchDetailState.Success(uiModel)

                    } else if (lResult is LaunchState.Error) {

                        LaunchDetailState.Error(lResult.message)

                    } else if (rResult is RocketState.Error) {

                        LaunchDetailState.Error(rResult.message)

                    } else {

                        LaunchDetailState.Loading
                    }
                }
                .collect { combinedState ->
                    _uiState.value = combinedState
                }
            }
        }
}