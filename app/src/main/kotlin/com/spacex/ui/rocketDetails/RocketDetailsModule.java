package com.spacex.ui.rocketDetails;

import android.os.Handler;
import com.spacex.rockets.repository.RocketsRepository;
import com.spacex.rockets.repository.RocketsRepositoryModule;
import com.spacex.ui.ExecutorsModule;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import java.util.concurrent.Executor;

@Module(includes = {RocketsRepositoryModule.class, ExecutorsModule.class})
public abstract class RocketDetailsModule {

  @Provides
  static RocketDetailsContract.Presenter providePresenter(
      RocketDetailsContract.View view, RocketDetailsContract.Coordinator coordinator) {
    return new RocketDetailsPresenter(
        view,
        ((RocketDetailsFragment) view)
            .getArguments()
            .getString(RocketDetailsContract.ARGUMENT_ROCKET_ID, RocketDetailsContract.NO_ARGUMENT),
        coordinator);
  }

  @Binds
  abstract RocketDetailsContract.View bindView(RocketDetailsFragment rocketDetailsFragment);

  @Provides
  static RocketDetailsContract.Coordinator provideCoordinator(
      RocketsRepository rocketsRepository, Handler mainThreadHandler, Executor backgroundExecutor) {
    return new RocketDetailsCoordinator(rocketsRepository, mainThreadHandler, backgroundExecutor);
  }
}
