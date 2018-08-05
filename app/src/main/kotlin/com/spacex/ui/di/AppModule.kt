package com.spacex.ui.di

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module

@Module
internal abstract class AppModule {

    @Binds
    internal abstract fun provideContext(application: Application): Context
}
