package com.spacex.launches.rockets.datasources.remote

import android.content.Context
import com.spacex.launches.rockets.model.RocketAM
import retrofit2.Call
import retrofit2.http.GET

internal class RocketsApiService internal constructor(context: Context) {

    private val rocketsService: RocketsService

    init {
        rocketsService = getRocketsRetrofit(context).create(RocketsService::class.java)
    }

    fun getAllRockets(): Call<List<RocketAM>> {
        return rocketsService.allRockets
    }

    private interface RocketsService {
        @get:GET("/v2/rockets")
        val allRockets: Call<List<RocketAM>>
    }
}