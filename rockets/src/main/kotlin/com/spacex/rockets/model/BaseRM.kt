package com.spacex.rockets.model

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

internal open class BaseRM(
        @PrimaryKey
        @ColumnInfo(name = "id")
        open var id: Int = 0,
        @ColumnInfo(name = "stored_timestamp")
        open var storedTimestamp: Long = 0)
