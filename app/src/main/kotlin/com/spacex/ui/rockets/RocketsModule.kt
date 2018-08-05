package com.spacex.ui.rockets

import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
internal abstract class RocketsModule {

    @Binds
    abstract fun bindView(rocketsActivity: RocketsActivity): RocketsContract.View

    @Module
    companion object {

        @JvmStatic
        @Provides
        fun providePresenter(
                view: RocketsContract.View
        ): RocketsContract.Presenter = RocketsPresenter(view)
    }
}
