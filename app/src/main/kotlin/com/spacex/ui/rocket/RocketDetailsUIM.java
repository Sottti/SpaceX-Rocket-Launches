package com.spacex.ui.rocket;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;

public class RocketDetailsUIM {

  private final int imageResId;
  private final String name;
  private final String description;

  public RocketDetailsUIM(
      @DrawableRes final int imageResId,
      @NonNull final String name,
      @NonNull final String description) {
    this.imageResId = imageResId;
    this.name = name;
    this.description = description;
  }

  @DrawableRes
  int getImageResId() {
    return imageResId;
  }

  @NonNull
  public String getName() {
    return name;
  }

  @NonNull
  public String getDescription() {
    return description;
  }
}
