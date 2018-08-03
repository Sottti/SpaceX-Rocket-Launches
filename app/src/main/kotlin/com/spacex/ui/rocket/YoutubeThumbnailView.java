package com.spacex.ui.rocket;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.databinding.BindingAdapter;
import com.spacex.ui.R;
import com.squareup.picasso.Picasso;

public class YoutubeThumbnailView extends AppCompatImageView {

  private static final String sQUALITY_DEFAULT = "default";
  private static final String sQUALITY_HQ = "hqdefault";
  private static final String sQUALITY_MQ = "mqdefault";
  private static final String sQUALITY_SD = "sddefault";
  private static final String sQUALITY_MAX_RES_DEFAULT = "maxresdefault";

  private static final String sURI_YOUTUBE_THUMBNAIL = "https://img.youtube.com/vi/%1$s/%2$s.jpg";

  public YoutubeThumbnailView(final Context context) {
    this(context, null);
  }

  public YoutubeThumbnailView(final Context context, @Nullable final AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public YoutubeThumbnailView(
      @NonNull final Context context, @Nullable final AttributeSet attrs, final int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    setScaleType(ScaleType.CENTER_CROP);
  }

  @BindingAdapter("videoKey")
  public static void loadVideoThumbnail(@NonNull final ImageView imageView, final String videoKey) {
    if (videoKey != null) {
      Picasso.with(imageView.getContext())
          .load(String.format(sURI_YOUTUBE_THUMBNAIL, videoKey, sQUALITY_MQ))
          .placeholder( R.color.grey_200)
          .error(R.color.grey_200)
          .into(imageView);
    } else {
      imageView.setImageResource(R.color.grey_200);
    }
  }
}
