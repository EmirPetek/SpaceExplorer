package org.lyrebird.spaceexplorer.data.local.dao.launch

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import org.lyrebird.spaceexplorer.data.local.entity.launch.LaunchEntity

@Dao
interface LaunchDao {
    @Query("SELECT * FROM launches ORDER BY dateUtc DESC")
    fun getAllLaunches(): Flow<List<LaunchEntity>>

    @Query("SELECT * FROM launches WHERE id = :id")
    fun getLaunchById(id: String): Flow<LaunchEntity?>

    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insertLaunches(launches: List<LaunchEntity>)
}