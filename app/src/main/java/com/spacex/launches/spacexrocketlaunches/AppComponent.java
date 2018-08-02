package com.spacex.launches.spacexrocketlaunches;

import android.app.Application;
import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import dagger.android.support.AndroidSupportInjectionModule;
import javax.inject.Singleton;

@Singleton
@Component(modules = {AndroidSupportInjectionModule.class, ActivityBuilder.class, AppModule.class})
public interface AppComponent extends AndroidInjector<DaggerApplication> {

  void inject(RocketLaunchesApp crowdScoresApplication);

  @Override
  void inject(DaggerApplication instance);

  @Component.Builder
  interface Builder {
    @BindsInstance
    Builder application(Application application);

    AppComponent build();
  }
}
