package com.spacex.launches.rockets.repository;

import com.spacex.launches.rockets.datasources.RocketsDS;
import com.spacex.launches.rockets.datasources.RocketsDSModule;
import dagger.Module;
import dagger.Provides;

@Module(includes = {RocketsDSModule.class})
public abstract class RocketsRepositoryModule {

  @Provides
  static RocketsRepository provideRepository(
      RocketsDS.Local RocketsLocalDS, RocketsDS.Remote RocketsRemoteDS) {
    return new RocketsRepositoryImpl(RocketsLocalDS, RocketsRemoteDS);
  }
}
