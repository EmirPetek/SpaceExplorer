package org.lyrebird.spaceexplorer.data.repo.rocket

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.lyrebird.spaceexplorer.data.local.dao.rocket.RocketDao
import org.lyrebird.spaceexplorer.data.network.KtorClient
import org.lyrebird.spaceexplorer.data.mapper.toDomainModel
import org.lyrebird.spaceexplorer.data.mapper.toEntity
import org.lyrebird.spaceexplorer.domain.model.rocket.Rocket
import org.lyrebird.spaceexplorer.domain.repo.rocket.IRocketRepository

class RocketRepository(
    val httpClient: HttpClient,
    val rocketDao: RocketDao
) : IRocketRepository {

    override suspend fun getRocketName(rocketId: String): String {

        val cachedName = rocketDao.getRocketNameById(rocketId)
        if (cachedName != null) {
            return cachedName
        }

        return try {
            val rocket: Rocket = httpClient.get(KtorClient.getUrl("rockets/$rocketId")).body()
            rocketDao.insertRocket(rocket.toEntity())
            rocket.name ?: "Unknown Rocket"
        } catch (e: Exception) {
            "Unknown Rocket"
        }
    }

    override fun getRocketDetails(id: String): Flow<Result<Rocket>> = flow {
        try {
            val response = httpClient.get(KtorClient.getUrl("rockets/$id")).body<Rocket>()
            rocketDao.insertRocket(response.toEntity())
        } catch (e: Exception) {

        }

        rocketDao.getRocketById(id).collect { entity ->
            if (entity != null) {
                emit(Result.success(entity.toDomainModel()))
            } else {
                emit(Result.failure(Exception("Rocket details not found.")))
            }
        }
    }
}