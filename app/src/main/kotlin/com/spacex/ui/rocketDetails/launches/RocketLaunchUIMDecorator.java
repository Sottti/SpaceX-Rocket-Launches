package com.spacex.ui.rocketDetails.launches;

import static android.text.format.DateUtils.FORMAT_ABBREV_MONTH;
import static android.text.format.DateUtils.FORMAT_NO_YEAR;
import static android.text.format.DateUtils.FORMAT_SHOW_DATE;
import static android.text.format.DateUtils.FORMAT_SHOW_WEEKDAY;
import static android.text.format.DateUtils.FORMAT_SHOW_YEAR;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.format.DateUtils;
import android.text.style.ForegroundColorSpan;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import com.spacex.ui.R;
import java.util.Calendar;

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

  @NonNull
  private static String getEitherDayAndMonthWithOptionallyWeekdayAndYearOrRelativeTimeSpan(
      final long timeInMillis) {
    final boolean isCurrentYear = isCurrentYear(timeInMillis);
    int flags =
        FORMAT_SHOW_DATE
            | (isCurrentYear ? FORMAT_NO_YEAR : FORMAT_SHOW_YEAR)
            | FORMAT_ABBREV_MONTH;

    if (isCurrentYear) {
      flags = flags | FORMAT_SHOW_WEEKDAY;
    }

    return DateUtils.getRelativeTimeSpanString(
            timeInMillis, System.currentTimeMillis(), DateUtils.DAY_IN_MILLIS, flags)
        .toString();
  }

  private static boolean isCurrentYear(final long timeInMillis) {
    return Calendar.getInstance().get(Calendar.YEAR) == getYear(timeInMillis);
  }

  private static int getYear(final long timeInMillis) {
    final Calendar calendar = Calendar.getInstance();
    calendar.setTimeInMillis(timeInMillis);
    return calendar.get(Calendar.YEAR);
  }

  RocketLaunchUIMDecorator onBind(@NonNull final RocketLaunchUIM rocketLaunchUIM) {
    this.rocketLaunchUIM = rocketLaunchUIM;
    return this;
  }

  private String getSuccessOrFailure(final boolean wasSuccessful) {
    return context.getString(wasSuccessful ? R.string.successful : R.string.failure);
  }
}
