package com.spacex.rockets.datasources.remote

import android.content.Context
import com.spacex.launches.rockets.BuildConfig
import com.squareup.moshi.Moshi
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.*
import java.util.concurrent.Executors


fun getRocketsRetrofit(context: Context): Retrofit {
    return getRetrofit(context)
        .newBuilder()
        .addConverterFactory(MoshiConverterFactory.create(rocketsMoshi))
        .build()
}

private fun getRetrofit(context: Context): Retrofit {
    return Retrofit.Builder()
        .client(getOkHttp(context))
        .baseUrl("https://api.spacexdata.com")
        .callbackExecutor(Executors.newSingleThreadExecutor())
        .build()
}

private val rocketsMoshi by lazy {
    Moshi.Builder().build()
}

private fun getOkHttp(context: Context): OkHttpClient {
    val cacheSize = 15 * 1024 * 1024
    return OkHttpClient.Builder()
        .cache(Cache(context.cacheDir, cacheSize.toLong()))
        .addInterceptor(httpLoggingInterceptor)
        .connectionSpecs(Collections.singletonList(spec))
        .build()
}

private val httpLoggingInterceptor by lazy {
    HttpLoggingInterceptor().setLevel(
        if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BASIC
        else HttpLoggingInterceptor.Level.NONE
    )
}

// Necessary for API 19 devices
// Reason -> https://github.com/square/okhttp/wiki/HTTPS
private val spec by lazy {
    ConnectionSpec.Builder(ConnectionSpec.COMPATIBLE_TLS)
        .supportsTlsExtensions(true)
        .tlsVersions(TlsVersion.TLS_1_2, TlsVersion.TLS_1_1, TlsVersion.TLS_1_0)
        .cipherSuites(
            CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256,
            CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256,
            CipherSuite.TLS_DHE_RSA_WITH_AES_128_GCM_SHA256,
            CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA,
            CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA,
            CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA,
            CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA,
            CipherSuite.TLS_ECDHE_ECDSA_WITH_RC4_128_SHA,
            CipherSuite.TLS_ECDHE_RSA_WITH_RC4_128_SHA,
            CipherSuite.TLS_DHE_RSA_WITH_AES_128_CBC_SHA,
            CipherSuite.TLS_DHE_DSS_WITH_AES_128_CBC_SHA,
            CipherSuite.TLS_DHE_RSA_WITH_AES_256_CBC_SHA
        )
        .build()
}
