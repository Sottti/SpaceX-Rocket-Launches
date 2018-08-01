package com.spacex.launches.spacexrocketlaunches.rocketList;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle.Event;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;
import com.spacex.launches.spacexrocketlaunches.rocketList.RocketListContract.Coordinator;
import com.spacex.launches.spacexrocketlaunches.rocketList.RocketListContract.Coordinator.OnGetRocketListCallbacks;
import com.spacex.launches.spacexrocketlaunches.rocketList.RocketListContract.Presenter;
import com.spacex.launches.spacexrocketlaunches.rocketList.RocketListContract.View;
import java.util.ArrayList;

public class RocketListPresenter implements Presenter, LifecycleObserver, OnGetRocketListCallbacks {

  private final RocketListContract.Coordinator coordinator;
  private RocketListContract.View view;

  RocketListPresenter(@NonNull final View view, final Coordinator coordinator) {
    this.view = view;
    this.coordinator = coordinator;
    if (view instanceof LifecycleOwner) {
      ((LifecycleOwner) view).getLifecycle().addObserver(this);
    }
  }

  @Override
  @OnLifecycleEvent(Event.ON_START)
  public void onStart() {
    getAllRockets();
  }

  private void getAllRockets() {
    coordinator.getAllRockets(this);
  }

  @Override
  public void onSuccessLoadingRocketList(@NonNull final ArrayList<RocketUIM> rockets) {
    if (view != null) {
      if (rockets.isEmpty()) {
        view.showAsEmpty();
      } else {
        view.showRockets(rockets);
      }
    }
  }

  @Override
  public void onRefresh() {
    getAllRockets();
  }

  @Override
  public void onRetry() {
    getAllRockets();
  }

  @Override
  public void onErrorLoadingRocketList() {
    view.showAsErrorLoading();
  }

  @Override
  public void onRocketClicked(final int rocketId) {
    view.navigateToRocketDetails(rocketId);
  }

  @Override
  public void onFilterByActiveRocketsClick() {
    coordinator.getActiveRockets(this);
  }

  @Override
  public void onShowWelcomeClick() {
    view.navigateToWelcomeActivity();
  }

  @Override
  public void onAboutWelcomeClick() {
    view.navigateToAboutActivity();
  }

  @Override
  @OnLifecycleEvent(Event.ON_STOP)
  public void onStop() {
    coordinator.cancel();
  }

  @Override
  @OnLifecycleEvent(Event.ON_DESTROY)
  public void onDestroy() {
    view = null;
  }
}
