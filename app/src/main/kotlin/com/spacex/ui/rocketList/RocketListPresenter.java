package com.spacex.ui.rocketList;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle.Event;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;
import com.spacex.ui.rocketList.RocketListContract.Coordinator.OnLoadRocketListCallbacks;
import com.spacex.ui.rocketList.RocketListContract.Presenter;
import com.spacex.ui.rocketList.RocketListContract.View;
import java.util.List;

public class RocketListPresenter
    implements Presenter, LifecycleObserver, OnLoadRocketListCallbacks {

  private final RocketListContract.Coordinator coordinator;
  private RocketListContract.View view;
  private boolean isShowingActiveRockets = false;
  private boolean isShowingFilterOptions = false;
  private boolean viewDoesNotHaveAnyData = true;

  RocketListPresenter(
      @NonNull final View view, @NonNull final RocketListContract.Coordinator coordinator) {
    this.view = view;
    this.coordinator = coordinator;
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
  @OnLifecycleEvent(Event.ON_START)
  public void onStart() {
    getAllRockets();
  }

  private void getAllRockets() {
    coordinator.loadAllRockets(this);
  }

  @Override
  public void onSuccessLoadingRocketList(@NonNull final List<RocketUIM> rockets) {
    if (view != null) {
      viewDoesNotHaveAnyData = false;
      if (rockets.isEmpty()) {
        view.showAsEmpty();
      } else {
        view.showRockets(rockets);
      }
    }
  }

  @Override
  public void onRefresh() {
    if (isShowingActiveRockets) {
      getActiveRockets();
    } else {
      getAllRockets();
    }
  }

  private void getActiveRockets() {
    coordinator.loadActiveRockets(this);
  }

  @Override
  public void onRetry() {
    getAllRockets();
  }

  @Override
  public void onErrorLoadingRocketList() {
    if (viewDoesNotHaveAnyData) {
      view.showAsErrorLoading();
    }
  }

  @Override
  public void onRocketClicked(final int rocketId) {
    view.navigateToRocketDetails(rocketId);
  }

  @Override
  public void onFilterRocketsClick() {
    view.showFilterOptions();
    isShowingFilterOptions = true;
  }

  @Override
  public void onShowAllRocketsFilterOptionClick() {
    view.hideFilterOptions();
    isShowingActiveRockets = false;
    view.showAllRocketsFilterAsSelected();
    getAllRockets();
    isShowingFilterOptions = true;
  }

  @Override
  public void onShowActiveRocketsFilterOptionClick() {
    hideFilterOptions();
    isShowingActiveRockets = true;
    view.showActiveRocketsFilterAsSelected();
    getActiveRockets();
  }

  @Override
  public void onCloseFilterOptions() {
    hideFilterOptions();
  }

  private void hideFilterOptions() {
    view.hideFilterOptions();
    isShowingFilterOptions = false;
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
  public void onBackNavigationPressed() {
    if (isShowingFilterOptions) {
      hideFilterOptions();
    } else {
      view.navigateBack();
    }
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
