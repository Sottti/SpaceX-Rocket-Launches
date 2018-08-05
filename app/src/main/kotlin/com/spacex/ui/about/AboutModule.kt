package com.spacex.ui.about

import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
internal abstract class AboutModule {

    @Binds
    abstract fun bindView(rocketsActivity: AboutActivity): AboutContract.View

    @Module
    companion object {

        @JvmStatic
        @Provides
        fun providePresenter(view: AboutContract.View): AboutContract.Presenter {
            return AboutPresenter(view)
        }
    }
}
