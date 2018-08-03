package com.spacex.ui.welcome;

interface WelcomeContract {

  interface View {
    void navigateToRocketList();
  }

  interface Presenter {
    void onButtonClick();
  }
}
