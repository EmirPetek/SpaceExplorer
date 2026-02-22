package org.lyrebird.spaceexplorer.ui.viewmodel.Launch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.lyrebird.spaceexplorer.ui.state.launch.LaunchListState
import org.lyrebird.spaceexplorer.domain.usecase.launch.LaunchUseCase

class LaunchViewModel(
    val launchUseCase: LaunchUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<LaunchListState>(LaunchListState.Loading)
    val state = _state.asStateFlow()

    init {
        fetchLaunches()
    }

    fun fetchLaunches() {
        viewModelScope.launch {
            _state.value = LaunchListState.Loading
            launchUseCase.getLaunchList().collect { resultState ->
                _state.value = resultState
            }
        }
    }

}