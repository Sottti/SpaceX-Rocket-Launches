package com.spacex.ui.di;

import com.spacex.ui.rocketList.RocketListActivity;
import com.spacex.ui.rocketList.RocketListModule;
import com.spacex.ui.welcome.WelcomeActivity;
import com.spacex.ui.welcome.WelcomeModule;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
abstract class ActivityBuilder {

  @ContributesAndroidInjector(modules = RocketListModule.class)
  abstract RocketListActivity bindMainActivity();

  @ContributesAndroidInjector(modules = WelcomeModule.class)
  abstract WelcomeActivity bindWelcomeActivity();
}
