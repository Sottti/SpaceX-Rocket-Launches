package com.spacex.ui.rocketDetails

import android.os.Handler
import com.spacex.rockets.repository.RocketsRepository
import com.spacex.rockets.repository.RocketsRepositoryModule
import com.spacex.ui.common.ExecutorsModule
import com.spacex.ui.rocketDetails.RocketDetailsFragment.Companion.ARGUMENT_ROCKET_ID
import com.spacex.ui.rocketDetails.RocketDetailsFragment.Companion.NO_ARGUMENT
import dagger.Binds
import dagger.Module
import dagger.Provides
import java.util.concurrent.Executor

@Module(includes = [RocketsRepositoryModule::class, ExecutorsModule::class])
internal abstract class RocketDetailsModule {

    @Binds
    abstract fun bindView(rocketDetailsFragment: RocketDetailsFragment): RocketDetailsContract.View

    @Module
    companion object {

        @JvmStatic
        @Provides
        fun providePresenter(
                view: RocketDetailsContract.View,
                coordinator: RocketDetailsContract.Coordinator
        ): RocketDetailsContract.Presenter = RocketDetailsPresenter(
                view,
                (view as RocketDetailsFragment).arguments!!.getString(
                        ARGUMENT_ROCKET_ID,
                        NO_ARGUMENT),
                coordinator)

        @JvmStatic
        @Provides
        fun provideCoordinator(
                rocketsRepository: RocketsRepository,
                mainThreadHandler: Handler,
                backgroundExecutor: Executor): RocketDetailsContract.Coordinator =
                RocketDetailsCoordinator(rocketsRepository, mainThreadHandler, backgroundExecutor)

    }
}
