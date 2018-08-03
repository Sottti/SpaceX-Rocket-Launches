package com.spacex.ui;

import android.app.Activity;
import com.spacex.ui.rocketList.RocketListActivity;
import com.spacex.ui.welcome.WelcomeActivity;

public class EntryActivity extends Activity {

  @Override
  protected void onStart() {
    super.onStart();
    showFirstVisibleActivity();
  }

  private void showFirstVisibleActivity() {
    if (InteractionsHistory.wasWelcomeShown()) {
      RocketListActivity.startActivity(this);
    } else {
      WelcomeActivity.startActivity(this);
    }
  }
}
