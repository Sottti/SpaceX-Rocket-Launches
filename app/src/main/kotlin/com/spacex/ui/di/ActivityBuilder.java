package com.spacex.ui.di;

import com.spacex.ui.rocketList.RocketListActivity;
import com.spacex.ui.rocketList.RocketListModule;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
abstract class ActivityBuilder {

  @ContributesAndroidInjector(modules = RocketListModule.class)
  abstract RocketListActivity bindMainActivity();
}
