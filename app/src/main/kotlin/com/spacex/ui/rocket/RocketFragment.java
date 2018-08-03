package com.spacex.ui.rocket;

import static com.spacex.ui.rocket.RocketContract.ARGUMENT_ROCKET_ID;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import com.spacex.ui.R;
import com.spacex.ui.databinding.RocketBinding;
import dagger.android.support.DaggerFragment;
import javax.inject.Inject;

public class RocketFragment extends DaggerFragment implements RocketContract.View {

  private Context context;
  private RocketBinding viewBinding;
  @Inject RocketContract.Presenter presenter;

  @NonNull
  public static RocketFragment newInstance(final int rocketId) {
    final Bundle bundle = new Bundle();
    bundle.putInt(ARGUMENT_ROCKET_ID, rocketId);
    final RocketFragment rocketFragment = new RocketFragment();
    rocketFragment.setArguments(bundle);
    return rocketFragment;
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
      viewBinding = DataBindingUtil.inflate(inflater, R.layout.rocket, container, false);
      presenter.onCreateViewForFirstTime();
    } else {
      presenter.onViewRecreated(this);
    }
    return viewBinding.getRoot();
  }

  @Override
  public void setUpViews(final int rocketId) {
    switch (rocketId) {
      case 1:
        viewBinding
            .getRoot()
            .setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary));
        break;
      case 2:
        viewBinding
            .getRoot()
            .setBackgroundColor(ContextCompat.getColor(context, R.color.amber_A400));
        break;
      case 3:
        viewBinding.getRoot().setBackgroundColor(ContextCompat.getColor(context, R.color.black_6));
        break;
      case 4:
        viewBinding.getRoot().setBackgroundColor(ContextCompat.getColor(context, R.color.black_54));
        break;
    }
  }

  @Override
  public void navigateUp() {}
}
