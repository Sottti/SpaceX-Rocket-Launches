package com.spacex.rockets.datasources.remote

import android.content.Context
import dagger.Module
import dagger.Provides

@Module
internal object RocketsApiServiceModule {

    @JvmStatic
    @Provides
    fun provideRocketsApiService(context: Context): RocketsApiService = RocketsApiService(context)
}
