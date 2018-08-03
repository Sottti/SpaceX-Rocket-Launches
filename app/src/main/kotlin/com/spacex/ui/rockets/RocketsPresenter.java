package com.spacex.ui.rockets;

import androidx.lifecycle.Lifecycle.Event;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;
import com.spacex.ui.rockets.RocketsContract.Presenter;
import com.spacex.ui.rockets.RocketsContract.View;

public class RocketsPresenter implements Presenter, LifecycleObserver {

  private final RocketsContract.View view;

  RocketsPresenter(final View view) {
    this.view = view;
    if (view instanceof LifecycleOwner) {
      ((LifecycleOwner) view).getLifecycle().addObserver(this);
    }
  }

  @Override
  @OnLifecycleEvent(Event.ON_CREATE)
  public void onCreate() {
    view.setUpViews();
  }

  @Override
  public void onUpNavigation() {
    view.navigateUp();
  }
}
