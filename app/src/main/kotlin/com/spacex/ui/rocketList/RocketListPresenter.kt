package com.spacex.ui.rocketList

import androidx.lifecycle.Lifecycle.Event
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import com.spacex.ui.rocketList.RocketListContract.Coordinator.OnLoadRocketListCallbacks
import com.spacex.ui.rocketList.RocketListContract.Presenter
import com.spacex.ui.rocketList.RocketListContract.View

internal class RocketListPresenter internal constructor(
        view: View, private val coordinator: RocketListContract.Coordinator) : Presenter, LifecycleObserver, OnLoadRocketListCallbacks {
    private var view: RocketListContract.View? = null
    private var isShowingActiveRockets = false
    private var isShowingFilterOptions = false
    private var viewDoesNotHaveAnyData = true

    init {
        this.view = view
        if (view is LifecycleOwner) {
            (view as LifecycleOwner).lifecycle.addObserver(this)
        }
    }

    @OnLifecycleEvent(Event.ON_CREATE)
    override fun onCreate() {
        view!!.setUpViews()
    }

    @OnLifecycleEvent(Event.ON_START)
    override fun onStart() {
        getAllRockets()
    }

    private fun getAllRockets() {
        coordinator.loadAllRockets(this)
    }

    override fun onSuccessLoadingRocketList(rockets: List<RocketUIM>) {
        if (view != null) {
            viewDoesNotHaveAnyData = false
            if (rockets.isEmpty()) {
                view!!.showAsEmpty()
            } else {
                view!!.showRockets(rockets)
            }
        }
    }

    override fun onRefresh() {
        if (isShowingActiveRockets) {
            getActiveRockets()
        } else {
            getAllRockets()
        }
    }

    private fun getActiveRockets() {
        coordinator.loadActiveRockets(this)
    }

    override fun onRetry() {
        getAllRockets()
    }

    override fun onErrorLoadingRocketList() {
        if (viewDoesNotHaveAnyData) {
            view!!.showAsErrorLoading()
        }
    }

    override fun onRocketClicked(rocketId: Int) {
        view!!.navigateToRocketDetails(rocketId)
    }

    override fun onFilterRocketsClick() {
        view!!.showFilterOptions()
        isShowingFilterOptions = true
    }

    override fun onShowAllRocketsFilterOptionClick() {
        view!!.hideFilterOptions()
        isShowingActiveRockets = false
        view!!.showAllRocketsFilterAsSelected()
        getAllRockets()
        isShowingFilterOptions = true
    }

    override fun onShowActiveRocketsFilterOptionClick() {
        hideFilterOptions()
        isShowingActiveRockets = true
        view!!.showActiveRocketsFilterAsSelected()
        getActiveRockets()
    }

    override fun onCloseFilterOptions() {
        hideFilterOptions()
    }

    private fun hideFilterOptions() {
        view!!.hideFilterOptions()
        isShowingFilterOptions = false
    }

    override fun onShowWelcomeClick() {
        view!!.navigateToWelcomeActivity()
    }

    override fun onAboutWelcomeClick() {
        view!!.navigateToAboutActivity()
    }

    override fun onBackNavigationPressed() {
        if (isShowingFilterOptions) {
            hideFilterOptions()
        } else {
            view!!.navigateBack()
        }
    }

    @OnLifecycleEvent(Event.ON_STOP)
    override fun onStop() {
        coordinator.cancel()
    }

    @OnLifecycleEvent(Event.ON_DESTROY)
    override fun onDestroy() {
        view = null
    }
}
