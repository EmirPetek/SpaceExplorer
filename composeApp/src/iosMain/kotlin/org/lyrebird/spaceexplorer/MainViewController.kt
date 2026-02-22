package org.lyrebird.spaceexplorer

import androidx.compose.ui.window.ComposeUIViewController
import org.lyrebird.spaceexplorer.core.di.initializeKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initializeKoin {
            modules()
        }
    }
) {
    App()
}