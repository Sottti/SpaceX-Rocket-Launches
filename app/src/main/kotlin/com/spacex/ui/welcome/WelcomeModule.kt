package com.spacex.ui.welcome

import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
internal abstract class WelcomeModule {

    @Binds
    abstract fun bindView(welcomeActivity: WelcomeActivity): WelcomeContract.View

    @Module
    companion object {
        @JvmStatic
        @Provides
        fun providePresenter(view: WelcomeContract.View): WelcomeContract.Presenter = WelcomePresenter(view)
    }
}