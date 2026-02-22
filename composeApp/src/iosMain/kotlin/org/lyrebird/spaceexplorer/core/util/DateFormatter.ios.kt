package org.lyrebird.spaceexplorer.core.util

import platform.Foundation.NSDateFormatter
import platform.Foundation.NSLocale
import platform.Foundation.currentLocale

actual fun formatLaunchDate(dateUtc: String?, isTime: Boolean): String {
    if (dateUtc == null) return "Unknown Date"

    val dateFormatter = NSDateFormatter()
    dateFormatter.dateFormat = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
    val date = dateFormatter.dateFromString(dateUtc) ?: return dateUtc

    val outputFormatter = NSDateFormatter()

    outputFormatter.dateFormat = if (isTime) "dd MMMM yyyy - HH:mm" else "dd MMMM yyyy"
    outputFormatter.locale = NSLocale.currentLocale

    return outputFormatter.stringFromDate(date)
}