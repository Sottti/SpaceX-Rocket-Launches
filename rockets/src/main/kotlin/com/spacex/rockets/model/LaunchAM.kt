package com.spacex.rockets.model

import androidx.room.PrimaryKey
import com.squareup.moshi.Json

internal class LaunchAM {

    @PrimaryKey
    @field:Json(name = "mission_name")
    var missionName: String = ""

    @field:Json(name = "launch_date_unix")
    var date: Long = 0

    @field:Json(name = "launch_year")
    var year: Int = 0

    @field:Json(name = "launch_success")
    var wasSuccessful: Boolean = false

    @field:Json(name = "links")
    var links: Links = Links()

    @field:Json(name = "rocket")
    var rocket: Rocket = Rocket()

    class Rocket {
        @field:Json(name = "rocket_id")
        var rocketId: String = ""
    }

    class Links {
        @field:Json(name = "video_link")
        var videoLink: String = ""

        @field:Json(name = "wikipedia")
        var wikipedia: String = ""

        @field:Json(name = "mission_patch_small")
        var missionPatch: String = ""
    }
}
