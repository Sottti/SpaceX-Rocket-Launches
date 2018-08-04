package com.spacex.ui.rocketList;

import androidx.annotation.NonNull;
import java.util.List;

interface RocketListContract {

  interface View {

    void setUpViews();

    void showAsLoading();

    void showAsEmpty();

    void showAsErrorLoading();

    void showRockets(@NonNull final List<RocketUIM> rockets);

    void showAllRocketsFilterAsSelected();

    void showActiveRocketsFilterAsSelected();

    void navigateToRocketDetails(final int rocketId);

    void navigateToWelcomeActivity();

    void navigateToAboutActivity();

    void showFilterOptions();

    void hideFilterOptions();

    void navigateBack();
  }

  interface Presenter {

    void onCreate();

    void onStart();

    void onRocketClicked(final int rocketId);

    void onFilterRocketsClick();

    void onShowAllRocketsFilterOptionClick();

    void onShowActiveRocketsFilterOptionClick();

    void onCloseFilterOptions();

    void onShowWelcomeClick();

    void onAboutWelcomeClick();

    void onRefresh();

    void onRetry();

    void onBackNavigationPressed();

    void onStop();

    void onDestroy();
  }

  interface Coordinator {
    void loadAllRockets(@NonNull final OnLoadRocketListCallbacks callbacks);

    void loadActiveRockets(@NonNull final OnLoadRocketListCallbacks callbacks);

    void cancel();

    interface OnLoadRocketListCallbacks {
      void onSuccessLoadingRocketList(@NonNull final List<RocketUIM> rockets);

      void onErrorLoadingRocketList();
    }
  }
}
