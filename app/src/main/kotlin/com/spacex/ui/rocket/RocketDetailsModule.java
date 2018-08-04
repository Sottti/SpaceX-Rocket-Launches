package com.spacex.ui.rocket;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class RocketDetailsModule {

  @Provides
  static RocketDetailsContract.Presenter providePresenter(RocketDetailsContract.View view) {
    return new RocketDetailsPresenter(
        view,
        ((RocketDetailsFragment) view)
            .getArguments()
            .getInt(RocketDetailsContract.ARGUMENT_ROCKET_ID,
                RocketDetailsContract.NO_ARGUMENT));
  }

  @Binds
  abstract RocketDetailsContract.View bindView(RocketDetailsFragment rocketDetailsFragment);
}
