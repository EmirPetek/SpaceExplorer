package org.lyrebird.spaceexplorer

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import org.koin.dsl.module
import org.lyrebird.spaceexplorer.core.di.initializeKoin

lateinit var appContext : Context

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (org.koin.core.context.GlobalContext.getOrNull() == null) {
            appContext = applicationContext
            initializeKoin {
                modules()
            }
        }

        setContent {
            App()
        }
    }
}

fun getAndroidContext() : Context {
    return appContext
}
