package com.spacex.rockets.datasources.local

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides

@Module
internal object RocketsDaoModule {

    @JvmStatic
    @Provides
    fun provideRocketsDao(context: Context): RocketsDao =
            Room.databaseBuilder(
                    context.applicationContext, RocketsDatabase::class.java, "Rockets Database")
                    .fallbackToDestructiveMigration()
                    .build().rocketsDao
}
