package com.spacex.rockets.datasources.remote

import android.content.Context
import com.spacex.launches.rockets.BuildConfig
import com.squareup.moshi.Moshi
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.Executors

private val rocketsMoshi: Moshi
    get() = Moshi.Builder().build()

fun getRocketsRetrofit(context: Context): Retrofit {
    return getRetrofit(context)
            .newBuilder()
            .addConverterFactory(MoshiConverterFactory.create(rocketsMoshi))
            .build()
}

fun getRetrofit(context: Context): Retrofit {
    return Retrofit.Builder()
            .client(getOkHttp(context))
            .baseUrl("https://api.spacexdata.com")
            .callbackExecutor(Executors.newSingleThreadExecutor())
            .build()
}

private fun getOkHttp(context: Context): OkHttpClient {
    val cacheSize = 15 * 1024 * 1024
    val cache = Cache(context.cacheDir, cacheSize.toLong())
    return OkHttpClient.Builder()
            .cache(cache)
            .addInterceptor(getHttpLoggingInterceptor())
            .build()
}

private fun getHttpLoggingInterceptor() = HttpLoggingInterceptor()
        .setLevel(if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BASIC else HttpLoggingInterceptor.Level.NONE)
