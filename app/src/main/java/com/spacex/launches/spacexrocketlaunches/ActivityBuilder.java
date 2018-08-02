package com.spacex.launches.spacexrocketlaunches;

import com.spacex.launches.spacexrocketlaunches.rocketList.RocketListActivity;
import com.spacex.launches.spacexrocketlaunches.rocketList.RocketListModule;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
abstract class ActivityBuilder {

  @ContributesAndroidInjector(modules = RocketListModule.class)
  abstract RocketListActivity bindMainActivity();
}
