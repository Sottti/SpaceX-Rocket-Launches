package com.spacex.launches.spacexrocketlaunches.welcome;

interface WelcomeContract {

  interface View {
    void navigateToRocketList();
  }

  interface Presenter {
    void onButtonClick();
  }
}
