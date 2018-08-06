package com.spacex.ui.rocketList

import androidx.lifecycle.Lifecycle.Event
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import com.spacex.ui.rocketList.RocketListContract.Coordinator.OnLoadRocketListCallbacks
import com.spacex.ui.rocketList.RocketListContract.Presenter
import com.spacex.ui.rocketList.RocketListContract.View

internal class RocketListPresenter(
        private var view: View?, private val coordinator: RocketListContract.Coordinator
) : Presenter, LifecycleObserver, OnLoadRocketListCallbacks {

    private var isShowingActiveRockets = false
    private var isShowingFilterOptions = false
    private var viewDoesNotHaveAnyData = true
    private var rocketIds: ArrayList<String>? = null

    init {
        if (view is LifecycleOwner) {
            (view as LifecycleOwner).lifecycle.addObserver(this)
        }
    }

    @OnLifecycleEvent(Event.ON_CREATE)
    override fun onCreate() {
        view?.setUpViews()
    }

    @OnLifecycleEvent(Event.ON_START)
    override fun onStart() {
        loadRockets()
    }

    private fun loadRockets() {
        if (isShowingActiveRockets) {
            loadActiveRockets()
        } else {
            loadAllRockets()
        }
    }

    private fun loadAllRockets() {
        coordinator.loadAllRockets(this)
    }

    private fun loadActiveRockets() {
        coordinator.loadActiveRockets(this)
    }

    override fun onSuccessLoadingRocketList(rockets: List<RocketUIM>) {
        viewDoesNotHaveAnyData = false
        if (rockets.isEmpty()) {
            view?.showAsEmpty()
        } else {
            view?.showRockets(rockets)
        }
        rocketIds = rockets.getIds()
    }

    override fun onRefresh() {
        loadRockets()
    }

    override fun onRetry() {
        loadRockets()
    }

    override fun onErrorLoadingRocketList() {
        if (viewDoesNotHaveAnyData) {
            view?.showAsErrorLoading()
        }
    }

    override fun onRocketClicked(rocketId: String) {
        view?.navigateToRocketDetails(rocketId, rocketIds)
    }

    override fun onFilterRocketsClick() {
        view?.showFilterOptions()
        isShowingFilterOptions = true
    }

    override fun onShowAllRocketsFilterOptionClick() {
        view?.hideFilterOptions()
        isShowingActiveRockets = false
        view?.showAllRocketsFilterAsSelected()
        loadAllRockets()
        isShowingFilterOptions = true
    }

    override fun onShowActiveRocketsFilterOptionClick() {
        hideFilterOptions()
        isShowingActiveRockets = true
        view?.showActiveRocketsFilterAsSelected()
        loadActiveRockets()
    }

    override fun onCloseFilterOptions() {
        hideFilterOptions()
    }

    private fun hideFilterOptions() {
        view?.hideFilterOptions()
        isShowingFilterOptions = false
    }

    override fun onShowWelcomeClick() {
        view?.navigateToWelcomeActivity()
    }

    override fun onThoughtsClick() {
        view?.navigateToThoughtsActivity()
    }

    override fun onAboutWelcomeClick() {
        view?.navigateToAboutActivity()
    }

    override fun onBackNavigationPressed() {
        if (isShowingFilterOptions) {
            hideFilterOptions()
        } else {
            view?.navigateBack()
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
