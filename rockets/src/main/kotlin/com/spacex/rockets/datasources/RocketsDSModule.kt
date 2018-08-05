package com.spacex.rockets.datasources

import com.spacex.rockets.datasources.local.*
import com.spacex.rockets.datasources.remote.RocketsApiService
import com.spacex.rockets.datasources.remote.RocketsApiServiceModule
import com.spacex.rockets.datasources.remote.RocketsDSApi
import dagger.Module
import dagger.Provides

@Module(includes = [(RocketsApiServiceModule::class), (RocketsDaoModule::class), (LaunchesDaoModule::class)])
internal object RocketsDSModule {

    @JvmStatic
    @Provides
    fun provideRocketsLocalDS(
            rocketsDao: RocketsDao,
            launchesDao: LaunchesDao
    ): RocketsDS.Local = RocketsDSRoom(rocketsDao, launchesDao)

    @JvmStatic
    @Provides
    fun provideRocketsRemoteDS(rocketsApiService: RocketsApiService): RocketsDS.Remote =
            RocketsDSApi(rocketsApiService)
}
