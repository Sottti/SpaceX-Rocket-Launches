package com.spacex.rockets.datasources.local

import androidx.room.*
import com.spacex.rockets.model.LaunchRM

@Dao
internal interface LaunchesDao {

    @Query("select * from Launches where  rocket_id = (:rocketId)")
    fun loadLaunchesForRocket(rocketId: String): List<LaunchRM>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrReplaceLaunches(launches: List<LaunchRM>)

    @Delete
    fun deleteLaunches(rockets: List<LaunchRM>)
}
