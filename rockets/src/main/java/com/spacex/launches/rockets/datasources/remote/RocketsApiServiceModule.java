package com.spacex.launches.rockets.datasources.remote;

import android.content.Context;
import androidx.annotation.NonNull;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class RocketsApiServiceModule {

  @Provides
  static RocketsApiService provideRocketsApiService(@NonNull final Context context) {
    return new RocketsApiService(context);
  }
}
