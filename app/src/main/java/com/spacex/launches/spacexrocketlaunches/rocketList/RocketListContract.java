package com.spacex.launches.spacexrocketlaunches.rocketList;

import androidx.annotation.NonNull;
import java.util.ArrayList;

interface RocketListContract {

  interface View {

    void showAsLoading();

    void showAsEmpty();

    void showAsErrorLoading();

    void showRockets(@NonNull final ArrayList<RocketUIM> rockets);

    void navigateToRocketDetails(final int rocketId);

    void navigateToWelcomeActivity();

    void navigateToAboutActivity();
  }

  interface Presenter {

    void onStart();

    void onRocketClicked(final int rocketId);

    void onFilterByActiveRocketsClick();

    void onShowWelcomeClick();

    void onAboutWelcomeClick();

    void onRefresh();

    void onRetry();

    void onStop();

    void onDestroy();
  }

  interface Coordinator {
    void getAllRockets(@NonNull final OnGetRocketListCallbacks onGetRocketListCallbacks);

    void getActiveRockets(@NonNull final OnGetRocketListCallbacks onGetRocketListCallbacks);

    void cancel();

    interface OnGetRocketListCallbacks {
      void onSuccessLoadingRocketList(@NonNull final ArrayList<RocketUIM> rockets);

      void onErrorLoadingRocketList();
    }
  }
}
