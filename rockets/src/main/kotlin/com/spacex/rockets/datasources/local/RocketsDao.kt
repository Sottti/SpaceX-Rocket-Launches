package com.spacex.rockets.datasources.local

import androidx.room.*
import com.spacex.rockets.model.RocketRM

@Dao
interface RocketsDao {

    @Query("select * from Rockets where string_id = (:rocketId)")
    fun loadRocket(rocketId: String): RocketRM?

    @Query("select * from Rockets")
    fun loadAllRockets(): List<RocketRM>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrReplaceRockets(rockets: List<RocketRM>)

    @Delete
    fun deleteRockets(rockets: List<RocketRM>)
}
