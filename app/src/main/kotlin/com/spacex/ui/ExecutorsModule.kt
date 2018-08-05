package com.spacex.ui

import android.os.Handler
import android.os.Looper
import dagger.Module
import dagger.Provides
import java.util.concurrent.Executor
import java.util.concurrent.Executors

@Module
object ExecutorsModule {

    @JvmStatic
    @Provides
    fun provideBackgroundExecutor(): Executor = Executors.newSingleThreadExecutor()

    @JvmStatic
    @Provides
    fun provideMainThreadHandler(): Handler = Handler(Looper.getMainLooper())
}
