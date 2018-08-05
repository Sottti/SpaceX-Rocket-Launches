package com.spacex.rockets.model

import com.squareup.moshi.Json

internal class RocketAM {

    @field:Json(name = "rocketid")
    var rocketId: Int = 0

    @field:Json(name = "id")
    var id: String = ""

    @field:Json(name = "name")
    var name: String = ""

    @field:Json(name = "active")
    var active: Boolean = false

    @field:Json(name = "country")
    var country: String = ""

    @field:Json(name = "engines")
    var engines: Engines = Engines()

    class Engines {
        @field:Json(name = "number")
        var number: Int = 0
    }

    @field:Json(name = "description")
    var description: String = ""
}