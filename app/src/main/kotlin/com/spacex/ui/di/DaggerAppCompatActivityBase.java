package com.spacex.ui.di;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;
import javax.inject.Inject;

public abstract class DaggerAppCompatActivityBase extends AppCompatActivity
    implements HasSupportFragmentInjector {

  static {
    AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
  }

  @Inject DispatchingAndroidInjector<Fragment> fragmentDispatchingAndroidInjector;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    AndroidInjection.inject(this);
    super.onCreate(savedInstanceState);
  }

  @Override
  public AndroidInjector<Fragment> supportFragmentInjector() {
    return fragmentDispatchingAndroidInjector;
  }
}
