package com.spacex.ui.rocket;

import android.content.Context;
import android.graphics.drawable.Drawable;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

public class RocketDetailsUIMDecorator {

  private final Context context;
  private RocketDetailsUIM mRocketDetailsUIM;

  public RocketDetailsUIMDecorator(@NonNull final Context context) {
    this.context = context;
  }

  public void bind(@NonNull final RocketDetailsUIM rocketDetailsUIM) {
    this.mRocketDetailsUIM = rocketDetailsUIM;
  }

  public Drawable getImage() {
    return ContextCompat.getDrawable(context, mRocketDetailsUIM.getImageResId());
  }

  public String getName() {
    return mRocketDetailsUIM.getName();
  }

  public String getDescription() {
    return mRocketDetailsUIM.getDescription();
  }
}
