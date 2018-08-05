package com.spacex.rockets.datasources.local

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides

@Module
internal object LaunchesDaoModule {

    @JvmStatic
    @Provides
    fun provideLaunchesDao(context: Context): LaunchesDao =
            Room.databaseBuilder(
                    context.applicationContext, RocketsDatabase::class.java, "Rockets Database")
                    .fallbackToDestructiveMigration()
                    .build().launchesDao
}
