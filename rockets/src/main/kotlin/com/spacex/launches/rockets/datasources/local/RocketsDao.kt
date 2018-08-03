package com.spacex.launches.rockets.datasources.local

import androidx.room.*
import com.spacex.launches.rockets.model.RocketRM

@Dao
interface RocketsDao {

    @Query("select * from Rockets")
    fun loadAllRockets(): List<RocketRM>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrReplaceRockets(rockets: List<RocketRM>)

    @Delete
    fun deleteRockets(rockets: List<RocketRM>)
}
