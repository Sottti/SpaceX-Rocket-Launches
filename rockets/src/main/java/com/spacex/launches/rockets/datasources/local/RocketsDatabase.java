package com.spacex.launches.rockets.datasources.local;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.spacex.launches.rockets.model.RocketRM;

@Database(
    entities = {RocketRM.class},
    version = 1,
    exportSchema = false)
public abstract class RocketsDatabase extends RoomDatabase {
  private static final String sDATABASE_NAME = "Rockets Database";
  private static RocketsDatabase sInstance;

  public static RocketsDatabase getInstance(@NonNull final Context context) {
    if (sInstance == null) {
      sInstance =
          Room.databaseBuilder(
                  context.getApplicationContext(), RocketsDatabase.class, sDATABASE_NAME)
              .fallbackToDestructiveMigration()
              .build();
    }
    return sInstance;
  }

  public abstract RocketsDao getRocketsDao();
}
