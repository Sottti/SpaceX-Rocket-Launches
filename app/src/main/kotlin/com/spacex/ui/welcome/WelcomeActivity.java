package com.spacex.ui.welcome;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import com.spacex.ui.databinding.WelcomeBinding;
import com.spacex.ui.R;
import com.spacex.ui.rocketList.RocketListActivity;

public class WelcomeActivity extends AppCompatActivity implements WelcomeContract.View {

  private WelcomeContract.Presenter presenter;

  public static void startActivity(@NonNull final Context context) {
    context.startActivity(new Intent(context, WelcomeActivity.class));
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    final WelcomeBinding viewBinding = DataBindingUtil.setContentView(this, R.layout.welcome);
    presenter = new WelcomePresenter(this);
    viewBinding.button.setOnClickListener(view -> presenter.onButtonClick());
  }

  @Override
  public void navigateToRocketList() {
    RocketListActivity.startActivity(this);
  }
}
