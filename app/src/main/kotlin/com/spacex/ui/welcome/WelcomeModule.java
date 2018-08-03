package com.spacex.ui.welcome;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class WelcomeModule {

  @Provides
  static WelcomeContract.Presenter providePresenter(WelcomeContract.View view) {
    return new WelcomePresenter(view);
  }

  @Binds
  abstract WelcomeContract.View bindView(WelcomeActivity welcomeActivity);
}
