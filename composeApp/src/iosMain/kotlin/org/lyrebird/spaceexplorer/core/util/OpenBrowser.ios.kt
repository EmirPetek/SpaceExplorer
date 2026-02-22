package org.lyrebird.spaceexplorer.core.util

import platform.Foundation.NSURL
import platform.UIKit.UIApplication

actual fun openBrowser(url: String?) {
    val nsUrl = url?.let { NSURL.URLWithString(it) } ?: return

    if (UIApplication.sharedApplication.canOpenURL(nsUrl)) {
        UIApplication.sharedApplication.openURL(
            url = nsUrl,
            options = emptyMap<Any?, Any?>(),
            completionHandler = null
        )
    }
}