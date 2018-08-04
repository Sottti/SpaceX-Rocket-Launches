package com.spacex.ui.rocketDetails;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import com.spacex.ui.rocketDetails.launches.RocketLaunchItemUIM;
import java.util.List;

public class RocketDetailsUIM {

  private final int imageResId;
  private final String name;
  private final String description;
  private final List<RocketLaunchItemUIM> launches;

  public RocketDetailsUIM(
      @DrawableRes final int imageResId,
      @NonNull final String name,
      @NonNull final String description,
      @NonNull final List<RocketLaunchItemUIM> launches) {
    this.imageResId = imageResId;
    this.name = name;
    this.description = description;
    this.launches = launches;
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

  @NonNull
  public List<RocketLaunchItemUIM> getLaunches() {
    return launches;
  }
}
