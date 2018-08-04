package com.spacex.ui.rockets;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.core.app.NavUtils;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import com.spacex.ui.R;
import com.spacex.ui.databinding.RocketsBinding;
import com.spacex.ui.di.DaggerAppCompatActivityBase;
import com.spacex.ui.rocket.RocketDetailsFragment;
import javax.inject.Inject;

public class RocketsActivity extends DaggerAppCompatActivityBase
    implements RocketsContract.View {

  private RocketsBinding viewBinding;
  @Inject RocketsContract.Presenter presenter;

  public static void startActivity(@NonNull final Context context) {
    context.startActivity(new Intent(context, RocketsActivity.class));
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    viewBinding = DataBindingUtil.setContentView(this, R.layout.rockets);
  }

  @Override
  public void setUpViews() {
    setUpToolbar();
    setUpViewPager();
  }

  private void setUpToolbar() {
    setSupportActionBar(viewBinding.includeToolbar.toolbar);
    if (getSupportActionBar() != null) {
      getSupportActionBar().setDisplayShowTitleEnabled(false);
      getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
  }

  private void setUpViewPager() {
    viewBinding.viewPager.setAdapter(new RocketDetailsPagerAdapter(getSupportFragmentManager()));
  }

  private class RocketDetailsPagerAdapter extends FragmentPagerAdapter {
    RocketDetailsPagerAdapter(@NonNull final FragmentManager fragmentManager) {
      super(fragmentManager);
    }

    @Override
    public Fragment getItem(int position) {
      return RocketDetailsFragment.newInstance(position+1);
    }

    @Override
    public int getCount() {
      return 4;
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
