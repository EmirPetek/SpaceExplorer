package org.lyrebird.spaceexplorer.core.util

import androidx.room.TypeConverter
import kotlinx.serialization.json.Json

class StringListConverter {
    @TypeConverter
    fun fromList(list: List<String>?): String? {
        if (list == null) return null
        return Json.encodeToString(list)
    }

    @TypeConverter
    fun toList(data: String?): List<String>? {
        if (data == null) return null
        return try {
            Json.decodeFromString<List<String>>(data)
        } catch (e: Exception) {
            emptyList()
        }
    }
}