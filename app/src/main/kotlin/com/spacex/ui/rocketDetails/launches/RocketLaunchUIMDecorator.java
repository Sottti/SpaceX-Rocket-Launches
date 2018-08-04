package com.spacex.ui.rocketDetails.launches;

import static com.spacex.ui.rocketDetails.launches.LongExtensionFunctionsKt.getEitherDayAndMonthWithOptionallyWeekdayAndYearOrRelativeTimeSpan;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import com.spacex.ui.R;

public class RocketLaunchUIMDecorator {

  private final Context context;
  private RocketLaunchUIM rocketLaunchUIM;

  RocketLaunchUIMDecorator(@NonNull final Context context) {
    this.context = context;
  }

  @NonNull
  public String getVideoKey() {
    final String videoUrl = rocketLaunchUIM.getVideoUrl();
    return videoUrl.substring(videoUrl.indexOf("=") + 1, videoUrl.length());
  }

  @NonNull
  public String getMissionName() {
    return rocketLaunchUIM.getMissionName();
  }

  @NonNull
  public String getMissionPatchLink() {
    return rocketLaunchUIM.getMissionPatchUrl();
  }

  @NonNull
  public SpannableString getMissionDetails() {
    final String missionDetails =
        context.getString(
            R.string.rocket_launch_details_format,
            getEitherDayAndMonthWithOptionallyWeekdayAndYearOrRelativeTimeSpan(
                rocketLaunchUIM.getDate()),
            getSuccessOrFailure(rocketLaunchUIM.getWasSuccess()));
    final SpannableString missionDetailsSpan = new SpannableString(missionDetails);
    missionDetailsSpan.setSpan(
        new ForegroundColorSpan(
            ContextCompat.getColor(context, rocketLaunchUIM.getSuccessLabelColorResId())),
        missionDetails.indexOf("•") + 1,
        missionDetails.length(),
        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
    return missionDetailsSpan;
  }

  RocketLaunchUIMDecorator onBind(@NonNull final RocketLaunchUIM rocketLaunchUIM) {
    this.rocketLaunchUIM = rocketLaunchUIM;
    return this;
  }

  private String getSuccessOrFailure(final boolean wasSuccessful) {
    return context.getString(wasSuccessful ? R.string.successful : R.string.failure);
  }
}
