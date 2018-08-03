package com.spacex.ui;

import com.spacex.ui.di.AppComponent;
import com.squareup.leakcanary.LeakCanary;
import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import timber.log.Timber;

public class RocketLaunchesApp extends DaggerApplication {

  private static RocketLaunchesApp instance;

  public static RocketLaunchesApp getInstance() {
    return instance;
  }

  @Override
  protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
    final AppComponent appComponent = DaggerAppComponent.builder().application(this).build();
    appComponent.inject(this);
    return appComponent;
  }

  @Override
  public void onCreate() {
    super.onCreate();
    instance = this;
    initLeakCanary();
    initTimber();
  }

  private void initLeakCanary() {
    if (LeakCanary.isInAnalyzerProcess(this)) {
      // This process is dedicated to LeakCanary for heap analysis.
      // You should not init your app in this process.
      return;
    }
    LeakCanary.install(this);
  }

  private void initTimber() {
    if (BuildConfig.DEBUG) {
      Timber.plant(new Timber.DebugTree());
    }
  }
}
