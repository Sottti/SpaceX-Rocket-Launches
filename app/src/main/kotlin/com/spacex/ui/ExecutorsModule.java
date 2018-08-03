package com.spacex.ui;

import android.os.Handler;
import android.os.Looper;
import dagger.Module;
import dagger.Provides;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Module
public abstract class ExecutorsModule {

  @Provides
  static Executor provideBackgroundExecutor() {
    return Executors.newSingleThreadExecutor();
  }

  @Provides
  static Handler provideMainThreadHandler() {
    return new Handler(Looper.getMainLooper());
  }
}
