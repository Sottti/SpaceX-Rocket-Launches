package com.spacex.ui.rocketList;

import android.os.Handler;
import androidx.annotation.NonNull;
import com.spacex.domain.RocketDM;
import com.spacex.domain.RocketDM_ExtensionsKt;
import com.spacex.rockets.repository.RocketsRepository;
import com.spacex.rockets.repository.RocketsRepository.OnLoadRocketsCallbacks;
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
  public void loadAllRockets(@NonNull final OnLoadRocketListCallbacks callbacks) {
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
  public void loadActiveRockets(@NonNull final OnLoadRocketListCallbacks callbacks) {
    backgroundExecutor.execute(
        () ->
            rocketsRepository.loadAllRockets(
                new OnLoadRocketsCallbacks() {
                  @Override
                  public void onSuccessLoadingRockets(@NotNull final List<RocketDM> rockets) {
                    final List<RocketUIM> rocketsUIM =
                        RocketsUIM_ExtenstionsKt.mapToUIM(
                            RocketDM_ExtensionsKt.getActiveRockets(rockets));
                    mainThreadHandler.post(() -> callbacks.onSuccessLoadingRocketList(rocketsUIM));
                  }

                  @Override
                  public void onErrorLoadingRockets() {
                    callbacks.onErrorLoadingRocketList();
                  }
                }));
  }

  @Override
  public void cancel() {
    rocketsRepository.cancel();
  }
}
