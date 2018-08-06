package com.spacex.ui.common

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.annotation.NonNull
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import com.spacex.ui.R

object IntentUtils {

    fun loadChromeCustomTab(
            @NonNull context: Context, @NonNull url: String) {
        val customTabsIntent = CustomTabsIntent.Builder()
                .setToolbarColor(ContextCompat.getColor(context, R.color.colorPrimary))
                .setShowTitle(true)
                .setStartAnimations(context, R.anim.slide_in_from_right, R.anim.fade_out_medium)
                .setExitAnimations(context, R.anim.fade_in_medium, R.anim.slide_out_to_right)
                .build()

        customTabsIntent.launchUrl(context, Uri.parse(url))
    }

    fun openVideoInYoutubeApp(context: Context?, videoKey: String) {
        val uri = "http://www.youtube.com/watch?v=$videoKey"
        context?.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(uri)))
    }

}
