package com.spacex.ui.rocket;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class RocketModule {

  @Provides
  static RocketContract.Presenter providePresenter(RocketContract.View view) {
    return new RocketPresenter(
        view,
        ((RocketFragment) view)
            .getArguments()
            .getInt(RocketContract.ARGUMENT_ROCKET_ID, RocketContract.NO_ARGUMENT));
  }

  @Binds
  abstract RocketContract.View bindView(RocketFragment rocketFragment);
}
