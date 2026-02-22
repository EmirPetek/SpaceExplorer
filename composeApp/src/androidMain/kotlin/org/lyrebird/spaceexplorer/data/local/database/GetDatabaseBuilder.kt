package org.lyrebird.spaceexplorer.data.local.database

import androidx.room.Room
import androidx.room.RoomDatabase
import org.lyrebird.spaceexplorer.getAndroidContext

actual fun getDatabaseBuilder(): RoomDatabase.Builder<AppDatabase> {
    val appContext = getAndroidContext()
    val dbFile = appContext.getDatabasePath("space_explorer.db")
    return Room.databaseBuilder<AppDatabase>(
        context = appContext,
        name = dbFile.absolutePath
    )
}