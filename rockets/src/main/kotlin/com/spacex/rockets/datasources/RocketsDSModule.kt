package com.spacex.rockets.datasources

import com.spacex.rockets.datasources.local.RocketsDSRoom
import com.spacex.rockets.datasources.local.RocketsDao
import com.spacex.rockets.datasources.local.RocketsDaoModule
import com.spacex.rockets.datasources.remote.RocketsApiService
import com.spacex.rockets.datasources.remote.RocketsApiServiceModule
import com.spacex.rockets.datasources.remote.RocketsDSApi
import dagger.Module
import dagger.Provides

@Module(includes = [(RocketsApiServiceModule::class), (RocketsDaoModule::class)])
object RocketsDSModule {

    @JvmStatic
    @Provides
    fun provideRocketsLocalDS(rocketsDao: RocketsDao): RocketsDS.Local = RocketsDSRoom(rocketsDao)

    @JvmStatic
    @Provides
    fun provideRocketsRemoteDS(rocketsApiService: RocketsApiService): RocketsDS.Remote =
            RocketsDSApi(rocketsApiService)
}
