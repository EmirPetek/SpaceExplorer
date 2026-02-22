package org.lyrebird.spaceexplorer.data.local.entity.launch

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "launches")
data class LaunchEntity(
    @PrimaryKey val id: String,
    val rocketId: String,
    val missionName: String,
    val dateUtc: String?,
    val isSuccess: Boolean?,
    val launchDetails: String?,
    val patchUrl: String?,
    val flickrImages: List<String>?,
    val videoUrl: String?,
    val articleUrl: String?
)