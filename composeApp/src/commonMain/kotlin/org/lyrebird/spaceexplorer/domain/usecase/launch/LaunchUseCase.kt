package org.lyrebird.spaceexplorer.domain.usecase.launch

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import org.lyrebird.spaceexplorer.ui.state.launch.LaunchListState
import org.lyrebird.spaceexplorer.ui.state.launch.LaunchState
import org.lyrebird.spaceexplorer.data.mapper.mapToUIModel
import org.lyrebird.spaceexplorer.domain.model.rocket.Rocket
import org.lyrebird.spaceexplorer.domain.repo.launch.ILaunchRepository
import org.lyrebird.spaceexplorer.domain.repo.rocket.IRocketRepository
import kotlin.collections.get

class LaunchUseCase(
    private val launchRepository: ILaunchRepository,
    private val rocketRepository: IRocketRepository
) {

    fun getLaunchList(): Flow<LaunchListState> {
        return launchRepository.getLaunchList().map { result ->
            result.fold(
                onSuccess = { launches ->
                    val sortedLaunches = launches.sortedByDescending { it.dateUnix ?: 0 }

                    val uniqueRocketIds = sortedLaunches.mapNotNull { it.rocket }.distinct()

                    val rocketMap = uniqueRocketIds.associateWith { id ->
                        rocketRepository.getRocketName(id)
                    }

                    val uiModel = launches.map { launch ->
                        val tempRocket =
                            Rocket(id = launch.id ?: "", name = rocketMap[launch.rocket])
                        mapToUIModel(launch, tempRocket)
                    }

                    LaunchListState.Success(uiModel)
                },
                onFailure = { error ->
                    LaunchListState.Error(error.message ?: "Error fetching launches")
                }
            )
        }.catch { e ->
            emit(LaunchListState.Error(e.message ?: "Unexpected Error"))
        }
    }

    fun getLaunchById(launchId: String): Flow<LaunchState> {
        return launchRepository.getLaunchById(launchId).map { result ->
            result.fold(
                onSuccess = { launch -> LaunchState.Success(launch) },
                onFailure = { error -> LaunchState.Error(error.message ?: "Launch not found") }
            )
        }.catch { e ->
            emit(LaunchState.Error(e.message ?: "Error fetching launch detail"))
        }
    }
}