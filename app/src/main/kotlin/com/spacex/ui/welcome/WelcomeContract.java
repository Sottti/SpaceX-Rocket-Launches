package com.spacex.ui.welcome;

interface WelcomeContract {

  interface View {
    void setUpViews();

    void navigateToRocketList();
  }

  interface Presenter {

    void onStart();

    void onButtonClick();
  }
}
