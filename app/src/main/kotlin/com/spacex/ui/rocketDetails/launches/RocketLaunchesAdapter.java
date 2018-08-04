package com.spacex.ui.rocketDetails.launches;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.spacex.ui.R;
import com.spacex.ui.databinding.RocketLaunchVhBinding;
import com.spacex.ui.databinding.RocketLaunchesHeaderVhBinding;
import com.spacex.ui.rocketDetails.launches.RocketLaunchVH.OnRocketLaunchClickListener;
import java.util.List;

public class RocketLaunchesAdapter extends RecyclerView.Adapter<RocketLaunchesItemVH> {

  private final OnRocketLaunchClickListener onRocketLaunchClickListener;
  private List<RocketLaunchItemUIM> launches;

  public RocketLaunchesAdapter(
      @NonNull final List<RocketLaunchItemUIM> launches,
      @NonNull final OnRocketLaunchClickListener onRocketLaunchClickListener) {
    this.onRocketLaunchClickListener = onRocketLaunchClickListener;
    this.launches = launches;
  }

  public void refreshData(@NonNull final List<RocketLaunchItemUIM> launches) {
    DiffUtil.calculateDiff(new RocketLaunchesDiffCallback(this.launches, launches))
        .dispatchUpdatesTo(this);
    this.launches = launches;
  }

  @NonNull
  @Override
  public RocketLaunchesItemVH onCreateViewHolder(
      @NonNull final ViewGroup parent, final int viewType) {
    switch (viewType) {
      case R.layout.rocket_launches_header_vh:
        return new RocketLaunchesHeaderVH(
            RocketLaunchesHeaderVhBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false));
      case R.layout.rocket_launch_vh:
        return new RocketLaunchVH(
            RocketLaunchVhBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false),
            onRocketLaunchClickListener);
    }
    throw new IllegalArgumentException();
  }

  @Override
  public void onBindViewHolder(@NonNull final RocketLaunchesItemVH holder, final int position) {
    if (holder.getItemViewType() == R.layout.rocket_launches_header_vh) {
      ((RocketLaunchesHeaderVH) holder).onBind((RocketLaunchHeaderUIM) launches.get(position));
    } else {
      ((RocketLaunchVH) holder).onBind((RocketLaunchUIM) launches.get(position));
    }
  }

  @Override
  public int getItemViewType(final int position) {
    if (launches.get(position) instanceof RocketLaunchHeaderUIM) {
      return R.layout.rocket_launches_header_vh;
    } else {
      return R.layout.rocket_launch_vh;
    }
  }

  @Override
  public int getItemCount() {
    return launches.size();
  }
}
