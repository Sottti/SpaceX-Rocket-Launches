package com.spacex.ui.rocketDetails.launches;

import android.view.View;
import android.view.View.OnClickListener;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.spacex.ui.databinding.RocketLaunchVhBinding;

class RocketLaunchVH extends RecyclerView.ViewHolder implements OnClickListener {

  private final RocketLaunchVhBinding viewBinding;
  private final RocketLaunchUIMDecorator decorator;
  private final OnRocketLaunchClickListener clickListener;

  RocketLaunchVH(
      final RocketLaunchVhBinding viewBinding,
      @NonNull final OnRocketLaunchClickListener onRocketLaunchClickListener) {
    super(viewBinding.getRoot());
    this.viewBinding = viewBinding;
    clickListener = onRocketLaunchClickListener;
    decorator = new RocketLaunchUIMDecorator(itemView.getContext());
    viewBinding.getRoot().setOnClickListener(this);
  }

  void onBind(@NonNull final RocketLaunchUIM rocketLaunchUIM) {
    viewBinding.setUIM(decorator.onBind(rocketLaunchUIM));
  }

  @Override
  public void onClick(final View view) {
    if (!decorator.getVideoKey().isEmpty()) {
      clickListener.onClick(decorator.getVideoKey());
    }
  }

  public interface OnRocketLaunchClickListener {
    void onClick(final String videoKey);
  }
}
