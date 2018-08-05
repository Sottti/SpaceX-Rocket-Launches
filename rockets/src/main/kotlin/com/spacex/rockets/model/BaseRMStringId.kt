package com.spacex.rockets.model

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

internal open class BaseRMStringId(
        @PrimaryKey
        @ColumnInfo(name = "id")
        open var id: String = "",
        @ColumnInfo(name = "stored_timestamp")
        open var storedTimestamp: Long = 0)
