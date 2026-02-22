package org.lyrebird.spaceexplorer.data.local.entity.rocket

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "rockets")
data class RocketEntity(
    @PrimaryKey val id: String,
    val name: String,
    val description: String?,
    val heightMeters: Double?,
    val massKg: Long?,
    val wikipediaUrl: String?,
    val flickrImages: List<String>?
)