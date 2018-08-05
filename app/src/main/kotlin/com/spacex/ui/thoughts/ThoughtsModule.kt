package com.spacex.ui.thoughts

import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
internal abstract class ThoughtsModule {

    @Binds
    abstract fun bindView(rocketsActivity: ThoughtsActivity): ThoughtsContract.View

    @Module
    companion object {

        @JvmStatic
        @Provides
        fun providePresenter(view: ThoughtsContract.View): ThoughtsContract.Presenter {
            return ThoughtsPresenter(view)
        }
    }
}
