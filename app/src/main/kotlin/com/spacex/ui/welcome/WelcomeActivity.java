package com.spacex.ui.welcome;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import com.spacex.ui.R;
import com.spacex.ui.databinding.WelcomeBinding;
import com.spacex.ui.di.DaggerAppCompatActivityBase;
import com.spacex.ui.rocketList.RocketListActivity;
import javax.inject.Inject;

public class WelcomeActivity extends DaggerAppCompatActivityBase implements WelcomeContract.View {

  @Inject WelcomeContract.Presenter presenter;
  private WelcomeBinding viewBinding;

  public static void startActivity(@NonNull final Context context) {
    context.startActivity(new Intent(context, WelcomeActivity.class));
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    viewBinding = DataBindingUtil.setContentView(this, R.layout.welcome);
  }

  @Override
  public void setUpViews() {
    viewBinding.button.setOnClickListener(view -> presenter.onButtonClick());
  }

  @Override
  public void navigateToRocketList() {
    RocketListActivity.startActivity(this);
  }
}
