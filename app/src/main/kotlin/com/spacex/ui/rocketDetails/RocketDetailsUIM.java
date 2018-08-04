package com.spacex.ui.rocketDetails;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.spacex.ui.rocketDetails.launches.RocketLaunchItemUIM;
import java.util.List;

public class RocketDetailsUIM {

  private final int imageResId;
  private final String name;
  private final String description;
  private final List<RocketLaunchItemUIM> launches;
  private final LineGraphSeries<DataPoint> series;

  public RocketDetailsUIM(
      @DrawableRes final int imageResId,
      @NonNull final String name,
      @NonNull final String description,
      @NonNull final List<RocketLaunchItemUIM> launches,
      @NonNull final LineGraphSeries<DataPoint> series) {
    this.name = name;
    this.series = series;
    this.launches = launches;
    this.imageResId = imageResId;
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

  @NonNull
  public List<RocketLaunchItemUIM> getLaunches() {
    return launches;
  }

  @NonNull
  public LineGraphSeries<DataPoint> getChartSeries() {
    return series;
  }
}
