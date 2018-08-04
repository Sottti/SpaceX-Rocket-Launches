package com.spacex.ui.rocket;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle.Event;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;
import com.spacex.ui.rocket.RocketDetailsContract.Coordinator;
import com.spacex.ui.rocket.RocketDetailsContract.Coordinator.OnLoadRocketDetailsCallbacks;
import com.spacex.ui.rocket.RocketDetailsContract.Presenter;
import com.spacex.ui.rocket.RocketDetailsContract.View;

public class RocketDetailsPresenter
    implements Presenter, LifecycleObserver, OnLoadRocketDetailsCallbacks {

  private View view;
  private final int rocketId;
  private final Coordinator coordinator;

  RocketDetailsPresenter(
      final View view,
      final int rocketId,
      @NonNull final RocketDetailsContract.Coordinator coordinator) {
    this.view = view;
    this.rocketId = rocketId;
    this.coordinator = coordinator;
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
  @OnLifecycleEvent(Event.ON_START)
  public void onStart() {
    coordinator.getRocketDetailsUIM(this);
  }

  @Override
  public void onSuccessLoadingRocketDetails(@NonNull final RocketDetailsUIM rocketDetails) {
    if (view != null) {
      view.showRocketDetails(rocketDetails);
    }
  }

  @Override
  public void onErrorLoadingRocketDetails() {
    if (view != null) {
      view.showAsErrorLoadingRocketDetails();
    }
  }

  @Override
  @OnLifecycleEvent(Event.ON_STOP)
  public void onStop() {
    coordinator.cancel();
  }

  @Override
  public void onDestroyView() {
    view = null;
  }

  @Override
  @OnLifecycleEvent(Event.ON_DESTROY)
  public void onDestroy() {
    view = null;
  }
}
