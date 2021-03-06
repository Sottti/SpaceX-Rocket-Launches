package com.spacex.rockets.model

import androidx.room.ColumnInfo
import androidx.room.Entity


@Entity(tableName = "Rockets")
internal class RocketRM(
        id: Int,
        @ColumnInfo(name = "string_id")
        val stringId: String,
        @ColumnInfo(name = "name")
        val name: String,
        @ColumnInfo(name = "country")
        val country: String,
        @ColumnInfo(name = "engines_count")
        val enginesCount: String,
        @ColumnInfo(name = "is_active")
        val isActive: Boolean,
        @ColumnInfo(name = "description")
        val description: String,
        storedTimestamp: Long) : BaseRM(id, storedTimestamp)
