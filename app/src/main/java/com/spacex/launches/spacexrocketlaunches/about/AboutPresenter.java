package com.spacex.launches.spacexrocketlaunches.about;

import com.spacex.launches.spacexrocketlaunches.about.AboutContract.View;

public class AboutPresenter implements AboutContract.Presenter {

  private final View view;

  AboutPresenter(final View view) {
    this.view = view;
  }

  @Override
  public void onUpNavigation() {
    view.navigateUp();
  }
}
