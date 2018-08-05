package com.spacex.ui;

import android.content.Context;
import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.core.content.ContextCompat;

public class IntentUtils {

  public static void loadChromeCustomTab(
      @NonNull final Context context, @NonNull final String url) {
    final CustomTabsIntent customTabsIntent =
        new CustomTabsIntent.Builder()
            .setToolbarColor(ContextCompat.getColor(context, R.color.colorPrimary))
            .setShowTitle(true)
            .setStartAnimations(context, R.anim.slide_in_from_right, R.anim.fade_out_medium)
            .setExitAnimations(context, R.anim.fade_in_medium, R.anim.slide_out_to_right)
            .build();

    customTabsIntent.launchUrl(context, Uri.parse(url));
  }

}
