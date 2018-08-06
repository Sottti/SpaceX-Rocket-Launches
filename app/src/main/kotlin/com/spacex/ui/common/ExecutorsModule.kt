package com.spacex.ui.common

import android.os.Handler
import android.os.Looper
import dagger.Module
import dagger.Provides
import java.util.concurrent.Executor
import java.util.concurrent.Executors

@Module
internal object ExecutorsModule {

    @JvmStatic
    @Provides
    fun provideBackgroundExecutor(): Executor = Executors.newSingleThreadExecutor()

    @JvmStatic
    @Provides
    fun provideMainThreadHandler(): Handler = Handler(Looper.getMainLooper())
}
