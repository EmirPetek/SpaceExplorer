package org.lyrebird.spaceexplorer.data.local.dao.rocket

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import org.lyrebird.spaceexplorer.data.local.entity.rocket.RocketEntity

@Dao
interface RocketDao {
    @Query("SELECT * FROM rockets WHERE id = :id")
    fun getRocketById(id: String): Flow<RocketEntity?>

    @Query("SELECT name FROM rockets WHERE id = :id")
    suspend fun getRocketNameById(id: String): String?

    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insertRocket(rocket: RocketEntity)
}