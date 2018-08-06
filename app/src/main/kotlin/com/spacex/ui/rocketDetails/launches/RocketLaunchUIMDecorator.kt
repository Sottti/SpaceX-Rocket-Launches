package com.spacex.ui.rocketDetails.launches

import android.content.Context
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import androidx.core.content.ContextCompat
import com.spacex.ui.R

internal class RocketLaunchUIMDecorator(private val context: Context) {
    private var rocketLaunchUIM: RocketLaunchUIM? = null

    fun onBind(rocketLaunchUIM: RocketLaunchUIM): RocketLaunchUIMDecorator {
        this.rocketLaunchUIM = rocketLaunchUIM
        return this
    }

    val videoKey: String
        get() {
            val videoUrl = rocketLaunchUIM!!.videoUrl
            return videoUrl.substring(videoUrl.indexOf("=") + 1, videoUrl.length)
        }

    val missionName: String
        get() = rocketLaunchUIM!!.missionName

    val missionPatchLink: String
        get() = rocketLaunchUIM!!.missionPatchUrl

    val missionDetails: SpannableString
        get() {
            val missionDetails = context.getString(
                    R.string.rocket_launch_details_format,
                    rocketLaunchUIM!!.date.getEitherDayAndMonthWithOptionallyWeekdayAndYearOrRelativeTimeSpan(),
                    getSuccessOrFailure(rocketLaunchUIM!!.wasSuccess))
            val missionDetailsSpan = SpannableString(missionDetails)
            missionDetailsSpan.setSpan(
                    ForegroundColorSpan(
                            ContextCompat.getColor(context, rocketLaunchUIM!!.getSuccessLabelColorResId())),
                    missionDetails.indexOf("•") + 1,
                    missionDetails.length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            return missionDetailsSpan
        }

    private fun getSuccessOrFailure(wasSuccessful: Boolean): String {
        return context.getString(if (wasSuccessful) R.string.successful else R.string.failure)
    }
}
