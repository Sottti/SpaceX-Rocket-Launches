package com.spacex.ui.rocketDetails;

import android.os.Handler;
import androidx.annotation.NonNull;
import androidx.annotation.WorkerThread;
import com.spacex.domain.RocketDM;
import com.spacex.domain.RocketLaunchDM;
import com.spacex.rockets.repository.RocketsRepository;
import com.spacex.rockets.repository.RocketsRepository.OnLoadRocketCallbacks;
import com.spacex.rockets.repository.RocketsRepository.OnLoadRocketLaunchesCallbacks;
import java.util.List;
import java.util.concurrent.Executor;
import org.jetbrains.annotations.NotNull;

public class RocketDetailsCoordinator implements RocketDetailsContract.Coordinator {

  private final Handler mainThreadHandler;
  private final Executor backgroundExecutor;
  private final RocketsRepository rocketsRepository;

  RocketDetailsCoordinator(
      final RocketsRepository rocketsRepository,
      @NonNull final Handler mainThreadHandler,
      @NonNull final Executor backgroundExecutor) {
    this.rocketsRepository = rocketsRepository;
    this.mainThreadHandler = mainThreadHandler;
    this.backgroundExecutor = backgroundExecutor;
  }

  @Override
  public void getRocketDetailsUIM(
      @NonNull final String rocketId, @NotNull final OnLoadRocketDetailsCallbacks callbacks) {
    backgroundExecutor.execute(
        () ->
            rocketsRepository.loadRocket(
                rocketId,
                new OnLoadRocketCallbacks() {
                  @Override
                  public void onSuccessLoadingRocket(@NotNull final RocketDM rocket) {
                    onRocketReceived(rocket, callbacks);
                  }

                  @Override
                  public void onErrorLoadingRocket() {
                    mainThreadHandler.post(callbacks::onErrorLoadingRocketDetails);
                  }
                }));
  }

  @WorkerThread
  private void onRocketReceived(
      final @NotNull RocketDM rocket,
      final @NotNull RocketDetailsContract.Coordinator.OnLoadRocketDetailsCallbacks callbacks) {
    rocketsRepository.loadRocketLaunches(
        rocket.getStringId(),
        new OnLoadRocketLaunchesCallbacks() {
          @Override
          public void onSuccessLoadingRocketLaunches(@NotNull final List<RocketLaunchDM> launches) {
            final RocketDetailsUIM rocketDetailsUIM =
                RocketDetailsUIMExtensionsKt.mapToUIM(rocket, launches);
            mainThreadHandler.post(() -> callbacks.onSuccessLoadingRocketDetails(rocketDetailsUIM));
          }

          @Override
          public void onErrorLoadingRocketLaunches() {
            mainThreadHandler.post(callbacks::onErrorLoadingRocketDetails);
          }
        });
  }

  @Override
  public void cancel() {
    rocketsRepository.cancel();
  }
}
