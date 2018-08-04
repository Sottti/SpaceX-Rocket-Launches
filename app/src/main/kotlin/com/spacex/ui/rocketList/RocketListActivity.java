package com.spacex.ui.rocketList;

import static com.spacex.ui.AndroidOSKt.isAtLeastLollipop;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback;
import com.spacex.ui.R;
import com.spacex.ui.about.AboutActivity;
import com.spacex.ui.databinding.RocketListBinding;
import com.spacex.ui.di.DaggerAppCompatActivityBase;
import com.spacex.ui.rockets.RocketsActivity;
import com.spacex.ui.welcome.WelcomeActivity;
import java.util.List;
import javax.inject.Inject;

public class RocketListActivity extends DaggerAppCompatActivityBase
    implements RocketListContract.View {

  @Inject RocketListContract.Presenter presenter;
  private RocketListAdapter adapter;
  private RocketListBinding viewBinding;
  private BottomSheetBehavior<ConstraintLayout> bottomSheetBehavior;

  public static void startActivity(@NonNull final Context context) {
    context.startActivity(new Intent(context, RocketListActivity.class));
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    viewBinding = DataBindingUtil.setContentView(this, R.layout.rocket_list);
  }

  @Override
  public void setUpViews() {
    setUpToolbar();
    viewBinding.errorView.setOnRetryListener(() -> presenter.onRetry());
    viewBinding.recyclerView.setHasFixedSize(true);
    setUpSwipeRefresh();
    setUpBottomSheet();
  }

  private void setUpSwipeRefresh() {
    viewBinding.swipeRefreshLayout.setOnRefreshListener(() -> presenter.onRefresh());
    viewBinding.swipeRefreshLayout.setColorSchemeColors(
        ContextCompat.getColor(this, R.color.colorAccent));
  }

  private void setUpBottomSheet() {
    bottomSheetBehavior = BottomSheetBehavior.from(viewBinding.includeBottomSheet.bottomSheet);
    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
    bottomSheetBehavior.setBottomSheetCallback(
        new BottomSheetCallback() {
          @Override
          public void onStateChanged(@NonNull final View view, final int state) {
            if (state == BottomSheetBehavior.STATE_HIDDEN) {
              presenter.onCloseFilterOptions();
            }
          }

          @Override
          public void onSlide(@NonNull final View view, final float v) {}
        });
    viewBinding.scrim.setOnClickListener(view -> presenter.onCloseFilterOptions());
    if (isAtLeastLollipop()) {
      viewBinding.scrim.setElevation(viewBinding.includeToolbar.toolbar.getElevation());
    }

    viewBinding.includeBottomSheet.filterAllRocketsRow.setOnClickListener(
        view -> presenter.onShowAllRocketsFilterOptionClick());

    viewBinding.includeBottomSheet.filterActiveRocketsRow.setOnClickListener(
        view -> presenter.onShowActiveRocketsFilterOptionClick());
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
  public void showRockets(@NonNull final List<RocketUIM> rockets) {
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
  public void showAllRocketsFilterAsSelected() {
    viewBinding.includeBottomSheet.filterAllRocketsTick.setVisibility(View.VISIBLE);
    viewBinding.includeBottomSheet.filterActiveRocketsTick.setVisibility(View.GONE);
  }

  @Override
  public void showActiveRocketsFilterAsSelected() {
    viewBinding.includeBottomSheet.filterAllRocketsTick.setVisibility(View.GONE);
    viewBinding.includeBottomSheet.filterActiveRocketsTick.setVisibility(View.VISIBLE);
  }

  @Override
  public void navigateToRocketDetails(final int rocketId) {
    RocketsActivity.startActivity(this);
  }

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
        presenter.onFilterRocketsClick();
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

  @Override
  public void showFilterOptions() {
    showScrim();
    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
  }

  @Override
  public void hideFilterOptions() {
    hideScrim();
    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
  }

  private void showScrim() {
    viewBinding.scrim.setClickable(true);
    viewBinding
        .scrim
        .animate()
        .alpha(1f)
        .setDuration(this.getResources().getInteger(android.R.integer.config_shortAnimTime));
  }

  private void hideScrim() {
    viewBinding.scrim.setClickable(false);
    viewBinding
        .scrim
        .animate()
        .alpha(0f)
        .setDuration(this.getResources().getInteger(android.R.integer.config_shortAnimTime));
  }

  @Override
  public void onBackPressed() {
    presenter.onBackNavigationPressed();
  }

  @Override
  public void navigateBack() {
    super.onBackPressed();
  }
}
