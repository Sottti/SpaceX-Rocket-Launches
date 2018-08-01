package com.spacex.launches.spacexrocketlaunches.rocketList;

import androidx.annotation.NonNull;
import com.spacex.launches.spacexrocketlaunches.R;
import java.util.ArrayList;

public class RocketListCoordinator implements RocketListContract.Coordinator {

  @Override
  public void getAllRockets(@NonNull final OnGetRocketListCallbacks callbacks) {
    final ArrayList<RocketUIM> rockets = new ArrayList<>(8);
    rockets.add(new RocketUIM(1, "Falcon 1", "United States", "1", R.drawable.rocket_falcon_1));
    rockets.add(new RocketUIM(2, "Falcon 9", "United States", "9", R.drawable.rocket_falcon_9));
    rockets.add(
        new RocketUIM(3, "Falcon Heavy", "United States", "27", R.drawable.rocket_falcon_heavy));
    rockets.add(new RocketUIM(4, "BFR", "United States", "31", R.drawable.rocket_bfr));
    callbacks.onSuccessLoadingRocketList(rockets);
  }

  @Override
  public void getActiveRockets(@NonNull final OnGetRocketListCallbacks onGetRocketListCallbacks) {}

  @Override
  public void cancel() {}
}
