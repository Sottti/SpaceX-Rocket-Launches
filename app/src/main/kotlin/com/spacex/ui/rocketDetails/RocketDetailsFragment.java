package com.spacex.ui.rocketDetails;

import static com.spacex.ui.rocketDetails.RocketDetailsContract.ARGUMENT_ROCKET_ID;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import com.spacex.ui.R;
import com.spacex.ui.databinding.RocketDetailsBinding;
import com.spacex.ui.rocketDetails.launches.RocketLaunchesAdapter;
import dagger.android.support.DaggerFragment;
import javax.inject.Inject;
import org.jetbrains.annotations.NotNull;

public class RocketDetailsFragment extends DaggerFragment implements RocketDetailsContract.View {

  @Inject RocketDetailsContract.Presenter presenter;
  private RocketDetailsBinding viewBinding;
  private RocketLaunchesAdapter adapter;
  private Context context;

  @NonNull
  public static RocketDetailsFragment newInstance(final String rocketId) {
    final Bundle bundle = new Bundle();
    bundle.putString(ARGUMENT_ROCKET_ID, rocketId);
    final RocketDetailsFragment rocketDetailsFragment = new RocketDetailsFragment();
    rocketDetailsFragment.setArguments(bundle);
    return rocketDetailsFragment;
  }

  @Override
  public void onAttach(final Context context) {
    super.onAttach(context);
    this.context = context;
  }

  @Override
  public View onCreateView(
      @NonNull LayoutInflater inflater,
      @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    if (viewBinding == null) {
      viewBinding = DataBindingUtil.inflate(inflater, R.layout.rocket_details, container, false);
      presenter.onCreateViewForFirstTime();
    } else {
      presenter.onViewRecreated(this);
    }
    return viewBinding.getRoot();
  }

  @Override
  public void setUpViews() {
    viewBinding.recyclerView.setHasFixedSize(true);
  }

  @Override
  public void showRocketDetails(@NotNull final RocketDetailsUIM rocketDetails) {
    viewBinding.setUIM(new RocketDetailsUIMDecorator(context, rocketDetails));
    if (viewBinding.recyclerView.getAdapter() == null) {
      adapter =
          new RocketLaunchesAdapter(
              rocketDetails.getLaunches(), videoKey -> presenter.onLaunchClick(videoKey));
      viewBinding.recyclerView.setAdapter(adapter);
    } else {
      adapter.refreshData(rocketDetails.getLaunches());
    }
  }

  @Override
  public void showAsErrorLoadingRocketDetails() {}

  @Override
  public void openVideoInYoutubeApp(@NotNull final String videoKey) {
    final String uri = "http://www.youtube.com/watch?v=" + videoKey;
    context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(uri)));
  }
}
