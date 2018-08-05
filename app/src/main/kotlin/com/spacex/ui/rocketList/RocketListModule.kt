package com.spacex.ui.rocketList

import android.os.Handler
import com.spacex.rockets.repository.RocketsRepository
import com.spacex.rockets.repository.RocketsRepositoryModule
import com.spacex.ui.ExecutorsModule
import dagger.Binds
import dagger.Module
import dagger.Provides
import java.util.concurrent.Executor

@Module(includes = [RocketsRepositoryModule::class, ExecutorsModule::class])
abstract class RocketListModule {

    @Binds
    abstract fun bindView(rocketListActivity: RocketListActivity): RocketListContract.View

    @Module
    companion object {

        @JvmStatic
        @Provides
        fun providePresenter(
                view: RocketListContract.View,
                coordinator: RocketListContract.Coordinator
        ): RocketListContract.Presenter = RocketListPresenter(view, coordinator)

        @JvmStatic
        @Provides
        fun provideCoordinator(
                rocketsRepository: RocketsRepository,
                mainThreadHandler: Handler,
                backgroundExecutor: Executor): RocketListContract.Coordinator =
                RocketListCoordinator(rocketsRepository, mainThreadHandler, backgroundExecutor)
    }
}
