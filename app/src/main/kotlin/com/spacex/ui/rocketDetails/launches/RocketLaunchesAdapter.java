package com.spacex.ui.rocketDetails.launches;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.spacex.ui.databinding.RocketLaunchVhBinding;
import com.spacex.ui.rocketDetails.launches.RocketLaunchVH.OnRocketLaunchClickListener;
import java.util.List;

public class RocketLaunchesAdapter extends RecyclerView.Adapter<RocketLaunchVH> {

  private final OnRocketLaunchClickListener onRocketLaunchClickListener;
  private List<RocketLaunchUIM> launches;

  public RocketLaunchesAdapter(
      @NonNull final List<RocketLaunchUIM> launches,
      @NonNull final OnRocketLaunchClickListener onRocketLaunchClickListener) {
    this.onRocketLaunchClickListener = onRocketLaunchClickListener;
    this.launches = launches;
  }

  public void refreshData(@NonNull final List<RocketLaunchUIM> launches) {
    DiffUtil.calculateDiff(new RocketLaunchesDiffCallback(this.launches, launches))
        .dispatchUpdatesTo(this);
    this.launches = launches;
  }

  @NonNull
  @Override
  public RocketLaunchVH onCreateViewHolder(@NonNull final ViewGroup parent, final int viewType) {
    return new RocketLaunchVH(
        RocketLaunchVhBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false),
        onRocketLaunchClickListener);
  }

  @Override
  public void onBindViewHolder(@NonNull final RocketLaunchVH holder, final int position) {
    holder.onBind(launches.get(position));
  }

  @Override
  public int getItemCount() {
    return launches.size();
  }
}
