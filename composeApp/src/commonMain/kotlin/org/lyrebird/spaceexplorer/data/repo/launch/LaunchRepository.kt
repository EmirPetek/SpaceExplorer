package org.lyrebird.spaceexplorer.data.repo.launch

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.lyrebird.spaceexplorer.data.local.dao.launch.LaunchDao
import org.lyrebird.spaceexplorer.data.network.KtorClient
import org.lyrebird.spaceexplorer.data.mapper.toDomainModel
import org.lyrebird.spaceexplorer.data.mapper.toEntity
import org.lyrebird.spaceexplorer.domain.model.launch.Launch
import org.lyrebird.spaceexplorer.domain.repo.launch.ILaunchRepository

class LaunchRepository(
    val httpClient: HttpClient,
    val launchDao: LaunchDao
): ILaunchRepository {

    override fun getLaunchList(): Flow<Result<List<Launch>>> = flow {
        try {
            val response: List<Launch> = httpClient.get(KtorClient.getUrl("launches")).body()

            val entities = response.map { it.toEntity() }
            launchDao.insertLaunches(entities)

        } catch (e: Exception) {
             emit(Result.failure(e))
        }

        launchDao.getAllLaunches().collect { localEntities ->
            if (localEntities.isNotEmpty()) {
                val domainModels = localEntities.map { it.toDomainModel() }
                emit(Result.success(domainModels))
            } else {
                emit(Result.failure(Exception("No data available offline.")))
            }
        }
    }

    override fun getLaunchById(id: String): Flow<Result<Launch>> = flow {

        try {
            val response: Launch = httpClient.get(KtorClient.getUrl("launches/$id")).body()
            launchDao.insertLaunches(listOf(response.toEntity()))
        } catch (e: Exception) {

        }

        launchDao.getLaunchById(id).collect { entity ->
            if (entity != null) {
                emit(Result.success(entity.toDomainModel()))
            } else {
                emit(Result.failure(Exception("Launch not found in cache.")))
            }
        }
    }
}