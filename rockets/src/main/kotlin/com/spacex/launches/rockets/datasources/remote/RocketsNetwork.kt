package com.spacex.launches.rockets.datasources.remote

import android.content.Context
import com.squareup.moshi.Moshi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val topRegionsMoshi: Moshi
    get() = CommonNetwork.getMoshi().newBuilder()/*.add(TopRegionsJsonAdapter())*/.build()

fun getRocketsRetrofit(context: Context): Retrofit {
    return CommonNetwork.getRetrofit(context)
            .newBuilder()
            .addConverterFactory(MoshiConverterFactory.create(topRegionsMoshi))
            .build()
}
