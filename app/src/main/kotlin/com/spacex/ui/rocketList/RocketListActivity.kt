package com.spacex.ui.rocketList

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback
import com.spacex.ui.R
import com.spacex.ui.about.AboutActivity
import com.spacex.ui.customViews.ErrorView
import com.spacex.ui.databinding.RocketListBinding
import com.spacex.ui.di.DaggerAppCompatActivityBase
import com.spacex.ui.isAtLeastLollipop
import com.spacex.ui.rockets.RocketsActivity
import com.spacex.ui.welcome.WelcomeActivity
import javax.inject.Inject

class RocketListActivity : DaggerAppCompatActivityBase(), RocketListContract.View, ErrorView.OnRefreshListener, RocketVH.OnRocketClickListener {

    var presenter: RocketListContract.Presenter? = null
        @Inject set
    private var adapter: RocketListAdapter? = null
    private var viewBinding: RocketListBinding? = null
    private var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>? = null

    companion object {

        fun startActivity(context: Context) {
            context.startActivity(Intent(context, RocketListActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = DataBindingUtil.setContentView(this, R.layout.rocket_list)
    }

    override fun setUpViews() {
        setUpToolbar()
        viewBinding!!.errorView.setOnRetryListener(this)
        viewBinding!!.recyclerView.setHasFixedSize(true)
        setUpSwipeRefresh()
        setUpBottomSheet()
    }

    override fun onRefresh() {
        presenter!!.onRetry()
    }

    private fun setUpSwipeRefresh() {
        viewBinding!!.swipeRefreshLayout.setOnRefreshListener { presenter!!.onRefresh() }
        viewBinding!!.swipeRefreshLayout.setColorSchemeColors(
                ContextCompat.getColor(this, R.color.colorAccent))
    }

    private fun setUpBottomSheet() {
        bottomSheetBehavior = BottomSheetBehavior.from(viewBinding!!.includeBottomSheet.bottomSheet)
        bottomSheetBehavior!!.state = BottomSheetBehavior.STATE_HIDDEN
        bottomSheetBehavior!!.setBottomSheetCallback(
                object : BottomSheetCallback() {
                    override fun onStateChanged(view: View, state: Int) {
                        if (state == BottomSheetBehavior.STATE_HIDDEN) {
                            presenter!!.onCloseFilterOptions()
                        }
                    }

                    override fun onSlide(view: View, v: Float) {}
                })
        viewBinding!!.scrim.setOnClickListener { presenter!!.onCloseFilterOptions() }
        if (isAtLeastLollipop()) {
            viewBinding!!.scrim.elevation = viewBinding!!.includeToolbar.toolbar.elevation
        }

        viewBinding!!.includeBottomSheet.filterAllRocketsRow.setOnClickListener { presenter!!.onShowAllRocketsFilterOptionClick() }

        viewBinding!!.includeBottomSheet.filterActiveRocketsRow.setOnClickListener { presenter!!.onShowActiveRocketsFilterOptionClick() }
    }

    private fun setUpToolbar() {
        setSupportActionBar(viewBinding!!.includeToolbar.toolbar)
    }

    override fun showAsLoading() {
        viewBinding!!.errorView.visibility = View.GONE
        viewBinding!!.emptyView.visibility = View.GONE
        viewBinding!!.swipeRefreshLayout.visibility = View.GONE
        viewBinding!!.includeProgressBar.progressBar.visibility = View.VISIBLE
    }

    override fun showAsEmpty() {
        viewBinding!!.errorView.visibility = View.GONE
        viewBinding!!.emptyView.visibility = View.VISIBLE
        viewBinding!!.swipeRefreshLayout.visibility = View.GONE
        viewBinding!!.includeProgressBar.progressBar.visibility = View.GONE
    }

    override fun showAsErrorLoading() {
        viewBinding!!.emptyView.visibility = View.GONE
        viewBinding!!.errorView.visibility = View.VISIBLE
        viewBinding!!.swipeRefreshLayout.visibility = View.GONE
        viewBinding!!.includeProgressBar.progressBar.visibility = View.GONE
    }

    override fun showRockets(rockets: List<RocketUIM>) {
        viewBinding!!.swipeRefreshLayout.isRefreshing = false
        viewBinding!!.errorView.visibility = View.GONE
        viewBinding!!.emptyView.visibility = View.GONE
        viewBinding!!.swipeRefreshLayout.visibility = View.VISIBLE
        viewBinding!!.includeProgressBar.progressBar.visibility = View.GONE
        if (viewBinding!!.recyclerView.adapter == null) {
            adapter = RocketListAdapter(rockets, this)
            viewBinding!!.recyclerView.adapter = adapter
        } else {
            adapter!!.refreshData(rockets)
        }
    }

    override fun onClick(rocketId: String) {
        presenter!!.onRocketClicked(rocketId)
    }

    override fun showAllRocketsFilterAsSelected() {
        viewBinding!!.includeBottomSheet.filterAllRocketsTick.visibility = View.VISIBLE
        viewBinding!!.includeBottomSheet.filterActiveRocketsTick.visibility = View.GONE
    }

    override fun showActiveRocketsFilterAsSelected() {
        viewBinding!!.includeBottomSheet.filterAllRocketsTick.visibility = View.GONE
        viewBinding!!.includeBottomSheet.filterActiveRocketsTick.visibility = View.VISIBLE
    }

    override fun navigateToRocketDetails(rocketId: String, rocketIds: ArrayList<String>?) {
        RocketsActivity.startActivity(this, rocketIds, rocketId)
    }

    override fun navigateToWelcomeActivity() {
        WelcomeActivity.startActivity(this)
    }

    override fun navigateToAboutActivity() {
        AboutActivity.startActivity(this)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.rocket_list, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.filter_rockets -> {
                presenter!!.onFilterRocketsClick()
                return true
            }

            R.id.show_welcome -> {
                presenter!!.onShowWelcomeClick()
                return true
            }

            R.id.about -> {
                presenter!!.onAboutWelcomeClick()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun showFilterOptions() {
        showScrim()
        bottomSheetBehavior!!.state = BottomSheetBehavior.STATE_EXPANDED
    }

    override fun hideFilterOptions() {
        hideScrim()
        bottomSheetBehavior!!.state = BottomSheetBehavior.STATE_HIDDEN
    }

    private fun showScrim() {
        viewBinding!!.scrim.isClickable = true
        viewBinding!!
                .scrim
                .animate()
                .alpha(1f).duration = this.resources.getInteger(android.R.integer.config_shortAnimTime).toLong()
    }

    private fun hideScrim() {
        viewBinding!!.scrim.isClickable = false
        viewBinding!!
                .scrim
                .animate()
                .alpha(0f).duration = this.resources.getInteger(android.R.integer.config_shortAnimTime).toLong()
    }

    override fun onBackPressed() {
        presenter!!.onBackNavigationPressed()
    }

    override fun navigateBack() {
        super.onBackPressed()
    }
}
