package com.spacex.ui.welcome;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle.Event;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;
import com.spacex.ui.InteractionsHistory;
import com.spacex.ui.welcome.WelcomeContract.Presenter;
import com.spacex.ui.welcome.WelcomeContract.View;

public class WelcomePresenter implements Presenter, LifecycleObserver {

  private final WelcomeContract.View view;

  WelcomePresenter(@NonNull final View view) {
    this.view = view;
    if (view instanceof LifecycleOwner) {
      ((LifecycleOwner) view).getLifecycle().addObserver(this);
    }
  }

  @Override
  @OnLifecycleEvent(Event.ON_START)
  public void onStart() {
    view.setUpViews();
  }

  @Override
  public void onButtonClick() {
    view.navigateToRocketList();
    InteractionsHistory.setWelcomeAsShown();
  }
}
