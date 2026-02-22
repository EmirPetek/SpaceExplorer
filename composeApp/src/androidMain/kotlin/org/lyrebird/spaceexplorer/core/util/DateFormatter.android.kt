package org.lyrebird.spaceexplorer.core.util

import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone

actual fun formatLaunchDate(dateUtc: String?, isTime: Boolean): String {
    if (dateUtc == null) return "Unknown Date"
    return try {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault()).apply {
            timeZone = TimeZone.getTimeZone("UTC")
        }
        val date = inputFormat.parse(dateUtc) ?: return dateUtc

        val pattern = if (isTime) "dd MMMM yyyy - HH:mm" else "dd MMMM yyyy"

        val outputFormat = SimpleDateFormat(pattern, Locale.getDefault())
        outputFormat.format(date)
    } catch (e: Exception) {
        dateUtc
    }
}