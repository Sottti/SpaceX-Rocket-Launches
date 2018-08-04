package com.spacex.ui.rocketDetails;

import static com.spacex.ui.rocketDetails.RocketDetailsContract.ARGUMENT_ROCKET_ID;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import com.spacex.ui.R;
import com.spacex.ui.databinding.RocketDetailsBinding;
import dagger.android.support.DaggerFragment;
import javax.inject.Inject;
import org.jetbrains.annotations.NotNull;

public class RocketDetailsFragment extends DaggerFragment implements RocketDetailsContract.View {

  private RocketDetailsBinding viewBinding;
  @Inject RocketDetailsContract.Presenter presenter;
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
  public void setUpViews(final String rocketId) {

  }

  @Override
  public void showRocketDetails(@NotNull final RocketDetailsUIM rocketDetails) {
    viewBinding.setUIM(new RocketDetailsUIMDecorator(context, rocketDetails));
  }

  @Override
  public void showAsErrorLoadingRocketDetails() {}
}
