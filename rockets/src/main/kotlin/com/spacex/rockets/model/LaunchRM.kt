package com.spacex.rockets.model

import androidx.room.ColumnInfo
import androidx.room.Entity


@Entity(tableName = "Launches")
internal class LaunchRM(
        id: String,
        @ColumnInfo(name = "rocket_id")
        val rocketId: String,
        @ColumnInfo(name = "date")
        val date: Long,
        @ColumnInfo(name = "year")
        val year: Int,
        @ColumnInfo(name = "wasSuccessful")
        val wasSuccessful: Boolean,
        @ColumnInfo(name = "mission_patch_link")
        val missionPatchLink: String,
        @ColumnInfo(name = "video_link")
        val videoLink: String,
        @ColumnInfo(name = "wikipedia_link")
        val wikipediaLink: String,
        storedTimestamp: Long) : BaseRMStringId(id, storedTimestamp)
