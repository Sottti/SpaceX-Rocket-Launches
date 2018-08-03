package com.spacex.ui.rocket;

import androidx.annotation.NonNull;

class RocketContract {

  static final String ARGUMENT_ROCKET_ID = "rocketId";
  static final int NO_ARGUMENT = -1;

  interface View {

    void setUpViews(final int rocketId);

    void navigateUp();
  }

  interface Presenter {

    void onCreateViewForFirstTime();

    void onViewRecreated(@NonNull final RocketContract.View view);

    void onUpNavigation();

    void onStop();

    void onDestroyView();

    void onDestroy();
  }
}
