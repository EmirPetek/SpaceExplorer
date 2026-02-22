package org.lyrebird.spaceexplorer.core.util

import android.content.Intent
import android.net.Uri
import org.lyrebird.spaceexplorer.MainActivity // Kendi MainActivity paketini kontrol et
import androidx.core.net.toUri
import org.lyrebird.spaceexplorer.getAndroidContext

actual fun openBrowser(url: String?) {
    val currentContext = getAndroidContext() ?: return // Context yoksa hiçbir şey yapma
    if (url.isNullOrBlank()) return

    try {
        val intent = Intent(Intent.ACTION_VIEW, url.toUri()).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        currentContext.startActivity(intent)
    } catch (e: Exception) {
        e.printStackTrace()
    }
}