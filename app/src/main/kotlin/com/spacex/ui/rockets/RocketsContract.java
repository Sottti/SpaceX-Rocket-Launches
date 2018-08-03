package com.spacex.ui.rockets;

class RocketsContract {

  interface View {

    void setUpViews();

    void navigateUp();
  }

  interface Presenter {

    void onCreate();

    void onUpNavigation();
  }
}
