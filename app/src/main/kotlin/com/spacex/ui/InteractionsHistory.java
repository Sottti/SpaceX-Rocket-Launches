package com.spacex.ui;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class InteractionsHistory {

  private static final String wasWelcomeShown = "was-welcome-shown";

  private static final SharedPreferences sSharedPreferencesDefault =
      PreferenceManager.getDefaultSharedPreferences(RocketLaunchesApp.getInstance());

  static boolean wasWelcomeShown() {
    return sSharedPreferencesDefault.getBoolean(wasWelcomeShown, false);
  }

  public static void setWelcomeAsShown() {
    sSharedPreferencesDefault.edit().putBoolean(wasWelcomeShown, true).apply();
  }
}
