package org.lyrebird.spaceexplorer.domain.repo.rocket

import kotlinx.coroutines.flow.Flow
import org.lyrebird.spaceexplorer.domain.model.rocket.Rocket

interface IRocketRepository {

    suspend fun getRocketName(rocketId: String):String
    fun getRocketDetails(id: String): Flow<Result<Rocket>>
}