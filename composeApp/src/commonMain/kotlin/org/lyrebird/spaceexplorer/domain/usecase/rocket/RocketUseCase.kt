package org.lyrebird.spaceexplorer.domain.usecase.rocket

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import org.lyrebird.spaceexplorer.ui.state.rocket.RocketState
import org.lyrebird.spaceexplorer.domain.repo.rocket.IRocketRepository

class RocketUseCase(
    val rocketRepository: IRocketRepository
) {

    fun getRocketDetails(rocketId: String): Flow<RocketState> {
        return rocketRepository.getRocketDetails(rocketId).map { result ->
            result.fold(
                onSuccess = { rocket ->
                    RocketState.Success(rocket)
                },
                onFailure = { error ->
                    RocketState.Error(error.message ?: "Rocket details not found")
                }
            )
        }.catch { e ->
            emit(RocketState.Error(e.message ?: "An unexpected error occurred"))
        }
    }
}