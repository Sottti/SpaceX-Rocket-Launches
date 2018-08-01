package com.spacex.launches.spacexrocketlaunches.rocketList;

import android.view.View;
import android.view.View.OnClickListener;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.spacex.launches.spacexrocketlaunches.databinding.RocketVhBinding;

class RocketVH extends RecyclerView.ViewHolder implements OnClickListener {

  private final RocketVhBinding viewBinding;
  private final RocketUIMDecorator rocketDecorator;
  private final OnRocketClickListener onRocketClickListener;
  private int rocketId;

  RocketVH(
      @NonNull final RocketVhBinding viewBinding,
      final OnRocketClickListener onRocketClickListener) {
    super(viewBinding.getRoot());
    this.viewBinding = viewBinding;
    this.onRocketClickListener = onRocketClickListener;
    rocketDecorator = new RocketUIMDecorator(itemView.getContext());
    viewBinding.cardView.setOnClickListener(this);
  }

  void onBind(@NonNull final RocketUIM rocketUIM) {
    rocketId = rocketUIM.getId();
    viewBinding.setUIM(rocketDecorator.bind(rocketUIM));
  }

  @Override
  public void onClick(final View view) {
    onRocketClickListener.onClick(rocketId);
  }

  public interface OnRocketClickListener {
    void onClick(final int rocketId);
  }
}
