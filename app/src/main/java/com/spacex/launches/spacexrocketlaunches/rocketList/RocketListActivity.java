package com.spacex.launches.spacexrocketlaunches.rocketList;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import com.spacex.launches.spacexrocketlaunches.R;
import com.spacex.launches.spacexrocketlaunches.about.AboutActivity;
import com.spacex.launches.spacexrocketlaunches.databinding.RocketListBinding;
import com.spacex.launches.spacexrocketlaunches.welcome.WelcomeActivity;
import java.util.ArrayList;

public class RocketListActivity extends AppCompatActivity implements RocketListContract.View {

  private RocketListAdapter adapter;
  private RocketListBinding viewBinding;
  private RocketListContract.Presenter presenter;

  public static void startActivity(@NonNull final Context context) {
    context.startActivity(new Intent(context, RocketListActivity.class));
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    viewBinding = DataBindingUtil.setContentView(this, R.layout.rocket_list);
    presenter = new RocketListPresenter(this, new RocketListCoordinator());
    setUpToolbar();
    viewBinding.errorView.setOnRetryListener(() -> presenter.onRetry());
    viewBinding.swipeRefreshLayout.setOnRefreshListener(() -> presenter.onRefresh());
  }

  private void setUpToolbar() {
    setSupportActionBar(viewBinding.includeToolbar.toolbar);
  }

  @Override
  public void showAsLoading() {
    viewBinding.errorView.setVisibility(View.GONE);
    viewBinding.emptyView.setVisibility(View.GONE);
    viewBinding.swipeRefreshLayout.setVisibility(View.GONE);
    viewBinding.includeProgressBar.progressBar.setVisibility(View.VISIBLE);
  }

  @Override
  public void showAsEmpty() {
    viewBinding.errorView.setVisibility(View.GONE);
    viewBinding.emptyView.setVisibility(View.VISIBLE);
    viewBinding.swipeRefreshLayout.setVisibility(View.GONE);
    viewBinding.includeProgressBar.progressBar.setVisibility(View.GONE);
  }

  @Override
  public void showAsErrorLoading() {
    viewBinding.emptyView.setVisibility(View.GONE);
    viewBinding.errorView.setVisibility(View.VISIBLE);
    viewBinding.swipeRefreshLayout.setVisibility(View.GONE);
    viewBinding.includeProgressBar.progressBar.setVisibility(View.GONE);
  }

  @Override
  public void showRockets(@NonNull final ArrayList<RocketUIM> rockets) {
    viewBinding.swipeRefreshLayout.setRefreshing(false);
    viewBinding.errorView.setVisibility(View.GONE);
    viewBinding.emptyView.setVisibility(View.GONE);
    viewBinding.swipeRefreshLayout.setVisibility(View.VISIBLE);
    viewBinding.includeProgressBar.progressBar.setVisibility(View.GONE);
    if (viewBinding.recyclerView.getAdapter() == null) {
      adapter = new RocketListAdapter(rockets, rocketId -> presenter.onRocketClicked(rocketId));
      viewBinding.recyclerView.setAdapter(adapter);
    } else {
      adapter.refreshData(rockets);
    }
  }

  @Override
  public void navigateToRocketDetails(final int rocketId) {}

  @Override
  public void navigateToWelcomeActivity() {
    WelcomeActivity.startActivity(this);
  }

  @Override
  public void navigateToAboutActivity() {
    AboutActivity.startActivity(this);
  }

  @Override
  public boolean onCreateOptionsMenu(final Menu menu) {
    getMenuInflater().inflate(R.menu.rocket_list, menu);
    return super.onCreateOptionsMenu(menu);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.filter_rockets:
        presenter.onFilterByActiveRocketsClick();
        return true;

      case R.id.show_welcome:
        presenter.onShowWelcomeClick();
        return true;

      case R.id.about:
        presenter.onAboutWelcomeClick();
        return true;
    }
    return super.onOptionsItemSelected(item);
  }
}
