package com.spacex.rockets.datasources.local;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.spacex.rockets.model.RocketRM;

@Database(
    entities = {RocketRM.class},
    version = 1,
    exportSchema = false)
abstract class RocketsDatabase extends RoomDatabase {
  private static final String sDATABASE_NAME = "Rockets Database";
  private static RocketsDatabase sInstance;

  static RocketsDatabase getInstance(@NonNull final Context context) {
    if (sInstance == null) {
      sInstance =
          Room.databaseBuilder(
                  context.getApplicationContext(), RocketsDatabase.class, sDATABASE_NAME)
              .fallbackToDestructiveMigration()
              .build();
    }
    return sInstance;
  }

  abstract RocketsDao getRocketsDao();
}
