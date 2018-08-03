package com.spacex.rockets.repository

import com.spacex.rockets.datasources.RocketsDS
import com.spacex.rockets.datasources.RocketsDSModule
import dagger.Module
import dagger.Provides

@Module(includes = [(RocketsDSModule::class)])
object RocketsRepositoryModule {

    @JvmStatic
    @Provides
    fun provideRepository(
            RocketsLocalDS: RocketsDS.Local,
            RocketsRemoteDS: RocketsDS.Remote): RocketsRepository =
            RocketsRepositoryImpl(RocketsLocalDS, RocketsRemoteDS)
}
