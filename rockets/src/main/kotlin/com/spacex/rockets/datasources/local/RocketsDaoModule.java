package com.spacex.rockets.datasources.local;

import android.content.Context;
import androidx.annotation.NonNull;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class RocketsDaoModule {

  @Provides
  static RocketsDao provideRocketsDao(@NonNull final Context context) {
    return RocketsDatabase.getInstance(context).getRocketsDao();
  }
}
