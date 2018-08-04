package com.spacex.ui.rocketDetails.launches;

import android.view.View;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.BindingAdapter;
import com.squareup.picasso.Picasso;

public final class ImageLoadingBindingAdapters {

  private ImageLoadingBindingAdapters() {
    throw new AssertionError();
  }

  @BindingAdapter({"imageUrl"})
  public static void loadImage(@NonNull final ImageView imageView, @Nullable final String url) {
    if (url == null || url.isEmpty()) {
      imageView.setVisibility(View.GONE);
    } else {
      Picasso.with(imageView.getContext()).load(url).into(imageView);
    }
  }
}
