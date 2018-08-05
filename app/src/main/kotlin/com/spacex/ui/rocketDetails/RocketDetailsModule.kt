package com.spacex.ui.rocketDetails

import android.os.Handler
import com.spacex.rockets.repository.RocketsRepository
import com.spacex.rockets.repository.RocketsRepositoryModule
import com.spacex.ui.ExecutorsModule
import dagger.Binds
import dagger.Module
import dagger.Provides
import java.util.concurrent.Executor

@Module(includes = [RocketsRepositoryModule::class, ExecutorsModule::class])
abstract class RocketDetailsModule {

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
                        RocketDetailsContract.ARGUMENT_ROCKET_ID,
                        RocketDetailsContract.NO_ARGUMENT),
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
