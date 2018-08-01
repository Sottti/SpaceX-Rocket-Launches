package com.spacex.launches.spacexrocketlaunches;

import android.app.Activity;
import com.spacex.launches.spacexrocketlaunches.rocketList.RocketListActivity;
import com.spacex.launches.spacexrocketlaunches.welcome.WelcomeActivity;

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
