package com.spacex.rockets.datasources.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.spacex.rockets.model.LaunchRM
import com.spacex.rockets.model.RocketRM

@Database(entities = [(RocketRM::class), (LaunchRM::class)], version = 1, exportSchema = false)
internal abstract class RocketsDatabase : RoomDatabase() {
    abstract val rocketsDao: RocketsDao
    abstract val launchesDao: LaunchesDao
}
