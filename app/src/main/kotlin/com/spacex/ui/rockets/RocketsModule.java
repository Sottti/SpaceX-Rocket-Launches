package com.spacex.ui.rockets;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class RocketsModule {

  @Provides
  static RocketsContract.Presenter providePresenter(RocketsContract.View view) {
    return new RocketsPresenter(view);
  }

  @Binds
  abstract RocketsContract.View bindView(RocketsActivity rocketsActivity);
}
