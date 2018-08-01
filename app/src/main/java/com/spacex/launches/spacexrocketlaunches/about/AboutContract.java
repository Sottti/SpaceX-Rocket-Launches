package com.spacex.launches.spacexrocketlaunches.about;

class AboutContract {

  interface View {

    void navigateUp();
  }

  interface Presenter {

    void onUpNavigation();
  }
}
