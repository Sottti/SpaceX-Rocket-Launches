package com.spacex.ui.rocket;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import com.spacex.ui.rocket.RocketContract.Presenter;
import com.spacex.ui.rocket.RocketContract.View;

public class RocketPresenter implements Presenter, LifecycleObserver {

  private View view;
  private final int rocketId;

  RocketPresenter(final View view, final int rocketId) {
    this.view = view;
    this.rocketId = rocketId;
    if (view instanceof LifecycleOwner) {
      ((LifecycleOwner) view).getLifecycle().addObserver(this);
    }
  }

  @Override
  public void onCreateViewForFirstTime() {
    view.setUpViews(rocketId);
  }

  @Override
  public void onViewRecreated(@NonNull final View view) {
    this.view = view;
  }

  @Override
  public void onUpNavigation() {}

  @Override
  public void onStop() {}

  @Override
  public void onDestroyView() {
    view = null;
  }

  @Override
  public void onDestroy() {}
}
