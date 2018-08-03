package com.spacex.launches.rockets.datasources;

import android.content.Context;
import com.spacex.launches.rockets.datasources.local.RocketsDSRoom;
import com.spacex.launches.rockets.datasources.local.RocketsDao;
import com.spacex.launches.rockets.datasources.local.RocketsDaoModule;
import com.spacex.launches.rockets.datasources.remote.RocketsApiService;
import com.spacex.launches.rockets.datasources.remote.RocketsApiServiceModule;
import com.spacex.launches.rockets.datasources.remote.RocketsDSApi;
import dagger.Module;
import dagger.Provides;

@Module(includes = {RocketsApiServiceModule.class, RocketsDaoModule.class})
public abstract class RocketsDSModule {
  @Provides
  static RocketsDS.Local provideRocketsLocalDS(RocketsDao rocketsDao) {
    return new RocketsDSRoom(rocketsDao);
  }

  @Provides
  static RocketsDS.Remote provideRocketsRemoteDS(
      RocketsApiService rocketsApiService, Context context) {
    return new RocketsDSApi(rocketsApiService);
  }
}
