package com.spacex.launches.spacexrocketlaunches.welcome;

import androidx.annotation.NonNull;
import com.spacex.launches.spacexrocketlaunches.InteractionsHistory;
import com.spacex.launches.spacexrocketlaunches.welcome.WelcomeContract.View;

public class WelcomePresenter implements WelcomeContract.Presenter {

  private final WelcomeContract.View view;

  WelcomePresenter(@NonNull final View view) {
    this.view = view;
  }

  @Override
  public void onButtonClick() {
    view.navigateToRocketList();
    InteractionsHistory.setWelcomeAsShown();
  }
}
