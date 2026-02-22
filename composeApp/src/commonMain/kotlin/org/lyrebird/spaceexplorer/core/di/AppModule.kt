package org.lyrebird.spaceexplorer.core.di

import io.ktor.http.ContentType
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module
import org.lyrebird.spaceexplorer.data.local.database.AppDatabase
import org.lyrebird.spaceexplorer.data.local.database.getDatabaseBuilder
import org.lyrebird.spaceexplorer.data.repo.launch.LaunchRepository
import org.lyrebird.spaceexplorer.data.repo.rocket.RocketRepository
import org.lyrebird.spaceexplorer.domain.repo.launch.ILaunchRepository
import org.lyrebird.spaceexplorer.domain.repo.rocket.IRocketRepository
import org.lyrebird.spaceexplorer.domain.usecase.launch.LaunchUseCase
import org.lyrebird.spaceexplorer.domain.usecase.rocket.RocketUseCase
import org.lyrebird.spaceexplorer.ui.viewmodel.LaunchDetail.LaunchDetailViewModel
import org.lyrebird.spaceexplorer.ui.viewmodel.Launch.LaunchViewModel

val appModule = module {
    single {
        HttpClient {
            install(ContentNegotiation) {
                json(json = Json { ignoreUnknownKeys = true }, contentType = ContentType.Any)
            }
        }
    }
    single { getDatabaseBuilder().build() }

    single { get<AppDatabase>().launchDao() }
    single { get<AppDatabase>().rocketDao() }


    single<ILaunchRepository> { LaunchRepository(get(), get()) }
    single { LaunchUseCase(get(), get())}


    single<IRocketRepository> { RocketRepository(get(),get()) }
    single { RocketUseCase(get()) }

    factory { LaunchViewModel(get()) }
    factory { LaunchDetailViewModel(get(),get()) }
}

fun initializeKoin(config:KoinAppDeclaration? = null) =
    startKoin {
        config?.invoke(this)
        modules(appModule)

    }