package com.spacex.ui.di;

import com.spacex.ui.rocketDetails.RocketDetailsFragment;
import com.spacex.ui.rocketDetails.RocketDetailsModule;
import com.spacex.ui.rocketList.RocketListActivity;
import com.spacex.ui.rocketList.RocketListModule;
import com.spacex.ui.rockets.RocketsActivity;
import com.spacex.ui.rockets.RocketsModule;
import com.spacex.ui.welcome.WelcomeActivity;
import com.spacex.ui.welcome.WelcomeModule;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
abstract class ActivityBuilder {

  @ContributesAndroidInjector(modules = RocketListModule.class)
  abstract RocketListActivity bindRocketListActivity();

  @ContributesAndroidInjector(modules = RocketsModule.class)
  abstract RocketsActivity bindRocketsActivity();

  @ContributesAndroidInjector(modules = RocketDetailsModule.class)
  abstract RocketDetailsFragment bindRocketFragment();

  @ContributesAndroidInjector(modules = WelcomeModule.class)
  abstract WelcomeActivity bindWelcomeActivity();
}
