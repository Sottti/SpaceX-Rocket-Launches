package com.spacex.ui.about;

import com.spacex.ui.about.AboutContract.View;

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
