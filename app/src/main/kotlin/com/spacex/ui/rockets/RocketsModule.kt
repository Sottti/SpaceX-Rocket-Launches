package com.spacex.ui.rockets

import com.spacex.ui.rockets.RocketsContract.ROCKET_IDS
import com.spacex.ui.rockets.RocketsContract.SELECTED_ROCKET_ID
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
        ): RocketsContract.Presenter {
            return RocketsPresenter(
                    view,
                    (view as RocketsActivity).intent.getStringExtra(SELECTED_ROCKET_ID),
                    view.intent.getStringArrayListExtra(ROCKET_IDS)
            )
        }
    }
}
