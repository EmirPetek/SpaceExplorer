package org.lyrebird.spaceexplorer.domain.repo.launch

import kotlinx.coroutines.flow.Flow
import org.lyrebird.spaceexplorer.domain.model.launch.Launch

interface ILaunchRepository {

    fun getLaunchList() : Flow<Result<List<Launch>>>
    fun getLaunchById(id: String): Flow<Result<Launch>>
}