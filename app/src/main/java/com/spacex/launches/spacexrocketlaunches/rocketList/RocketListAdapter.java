package com.spacex.launches.spacexrocketlaunches.rocketList;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.spacex.launches.spacexrocketlaunches.databinding.RocketVhBinding;
import com.spacex.launches.spacexrocketlaunches.rocketList.RocketVH.OnRocketClickListener;
import java.util.List;

public class RocketListAdapter extends RecyclerView.Adapter<RocketVH> {

  private final OnRocketClickListener onRocketClickListener;
  private List<RocketUIM> rockets;

  RocketListAdapter(
      @NonNull final List<RocketUIM> rockets,
      @NonNull final OnRocketClickListener onRocketClickListener) {
    this.onRocketClickListener = onRocketClickListener;
    this.rockets = rockets;
  }

  void refreshData(@NonNull final List<RocketUIM> rockets) {
    DiffUtil.calculateDiff(new RocketsDiffCallback(this.rockets, rockets)).dispatchUpdatesTo(this);
    this.rockets = rockets;
  }

  @NonNull
  @Override
  public RocketVH onCreateViewHolder(@NonNull final ViewGroup parent, final int viewType) {
    return new RocketVH(
        RocketVhBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false),
        onRocketClickListener);
  }

  @Override
  public void onBindViewHolder(@NonNull final RocketVH holder, final int position) {
    holder.onBind(rockets.get(position));
  }

  @Override
  public int getItemCount() {
    return rockets.size();
  }
}
