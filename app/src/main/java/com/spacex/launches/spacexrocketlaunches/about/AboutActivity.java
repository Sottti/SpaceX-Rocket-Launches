package com.spacex.launches.spacexrocketlaunches.about;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;
import androidx.databinding.DataBindingUtil;
import com.spacex.launches.spacexrocketlaunches.R;
import com.spacex.launches.spacexrocketlaunches.databinding.AboutBinding;

public class AboutActivity extends AppCompatActivity implements AboutContract.View {

  private AboutBinding viewBinding;
  private AboutContract.Presenter presenter;

  public static void startActivity(@NonNull final Context context) {
    context.startActivity(new Intent(context, AboutActivity.class));
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    viewBinding = DataBindingUtil.setContentView(this, R.layout.about);
    presenter = new AboutPresenter(this);
    setUpToolbar();
  }

  private void setUpToolbar() {
    setSupportActionBar(viewBinding.includeToolbar.toolbar);
    if (getSupportActionBar() != null) {
      getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        presenter.onUpNavigation();
        return true;
    }
    return super.onOptionsItemSelected(item);
  }

  @Override
  public void navigateUp() {
    NavUtils.navigateUpFromSameTask(this);
  }
}
