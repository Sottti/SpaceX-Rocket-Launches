package com.spacex.ui.about;

class AboutContract {

  interface View {

    void navigateUp();
  }

  interface Presenter {

    void onUpNavigation();
  }
}
