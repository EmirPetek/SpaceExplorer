package org.lyrebird.spaceexplorer.data.local.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import androidx.room.TypeConverters
import org.lyrebird.spaceexplorer.core.util.StringListConverter
import org.lyrebird.spaceexplorer.data.local.dao.launch.LaunchDao
import org.lyrebird.spaceexplorer.data.local.dao.rocket.RocketDao
import org.lyrebird.spaceexplorer.data.local.entity.launch.LaunchEntity
import org.lyrebird.spaceexplorer.data.local.entity.rocket.RocketEntity

@Database(entities = [LaunchEntity::class, RocketEntity::class], version = 1)
@TypeConverters(StringListConverter::class)
@ConstructedBy(AppDatabaseConstructor::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun launchDao(): LaunchDao
    abstract fun rocketDao(): RocketDao
}

@Suppress("KotlinNoActualForExpect")
expect object AppDatabaseConstructor : RoomDatabaseConstructor<AppDatabase> {
    override fun initialize(): AppDatabase
}
