package com.spacex.launches.spacexrocketlaunches.rocketList;

import android.os.Handler;
import com.spacex.launches.rockets.repository.RocketsRepository;
import com.spacex.launches.rockets.repository.RocketsRepositoryModule;
import com.spacex.launches.spacexrocketlaunches.ExecutorsModule;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import java.util.concurrent.Executor;

@Module(includes = {RocketsRepositoryModule.class, ExecutorsModule.class})
public abstract class RocketListModule {

  @Provides
  static RocketListContract.Presenter providePresenter(
      RocketListContract.View view, RocketListContract.Coordinator coordinator) {
    return new RocketListPresenter(view, coordinator);
  }

  @Provides
  static RocketListContract.Coordinator provideCoordinator(
      RocketsRepository rocketsRepository, Handler mainThreadHandler, Executor backgroundExecutor) {
    return new RocketListCoordinator(rocketsRepository, mainThreadHandler, backgroundExecutor);
  }

  @Binds
  abstract RocketListContract.View bindView(RocketListActivity rocketListActivity);
}
