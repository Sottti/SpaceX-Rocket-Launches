package com.spacex.launches.spacexrocketlaunches.rocketList;

import android.content.Context;
import android.graphics.drawable.Drawable;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import com.spacex.launches.spacexrocketlaunches.R;

public class RocketUIMDecorator {

  private final Context context;
  private RocketUIM rocketUIM;

  RocketUIMDecorator(@NonNull final Context context) {
    this.context = context;
  }

  public RocketUIMDecorator bind(@NonNull final RocketUIM rocketUIM) {
    this.rocketUIM = rocketUIM;
    return this;
  }

  public Drawable getImage() {
    return ContextCompat.getDrawable(context, rocketUIM.getImageResId());
  }

  public String getName() {
    return rocketUIM.getName();
  }

  public String getDetails() {
    return context.getString(
        R.string.rocket_list_details_format, rocketUIM.getEnginesCount(), rocketUIM.getCountry());
  }
}
