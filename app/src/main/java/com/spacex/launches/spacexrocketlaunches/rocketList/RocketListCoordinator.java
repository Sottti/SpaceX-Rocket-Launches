package com.spacex.launches.spacexrocketlaunches.rocketList;

import android.os.Handler;
import androidx.annotation.NonNull;
import com.spacex.launches.domain.RocketDM;
import com.spacex.launches.rockets.repository.RocketsRepository;
import com.spacex.launches.rockets.repository.RocketsRepository.OnLoadRocketsCallbacks;
import java.util.List;
import java.util.concurrent.Executor;
import org.jetbrains.annotations.NotNull;

public class RocketListCoordinator implements RocketListContract.Coordinator {

  private final Handler mainThreadHandler;
  private final Executor backgroundExecutor;
  private final RocketsRepository rocketsRepository;

  RocketListCoordinator(
      final RocketsRepository rocketsRepository,
      @NonNull final Handler mainThreadHandler,
      @NonNull final Executor backgroundExecutor) {
    this.rocketsRepository = rocketsRepository;
    this.mainThreadHandler = mainThreadHandler;
    this.backgroundExecutor = backgroundExecutor;
  }

  @Override
  public void getAllRockets(@NonNull final OnGetRocketListCallbacks callbacks) {
    backgroundExecutor.execute(
        () ->
            rocketsRepository.loadAllRockets(
                new OnLoadRocketsCallbacks() {
                  @Override
                  public void onSuccessLoadingRockets(@NotNull final List<RocketDM> rockets) {
                    final List<RocketUIM> rocketsUIM = RocketsUIM_ExtenstionsKt.mapToUIM(rockets);
                    mainThreadHandler.post(() -> callbacks.onSuccessLoadingRocketList(rocketsUIM));
                  }

                  @Override
                  public void onErrorLoadingRockets() {
                    callbacks.onErrorLoadingRocketList();
                  }
                }));
  }

  @Override
  public void getActiveRockets(@NonNull final OnGetRocketListCallbacks onGetRocketListCallbacks) {

  }

  @Override
  public void cancel() {
    rocketsRepository.cancel();
  }
}
