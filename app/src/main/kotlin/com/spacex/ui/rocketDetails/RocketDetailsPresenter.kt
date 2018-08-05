package com.spacex.ui.rocketDetails

import androidx.lifecycle.Lifecycle.Event
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import com.spacex.ui.rocketDetails.RocketDetailsContract.Coordinator.OnLoadRocketDetailsCallbacks
import com.spacex.ui.rocketDetails.RocketDetailsContract.Presenter
import com.spacex.ui.rocketDetails.RocketDetailsContract.View

internal class RocketDetailsPresenter(
        private var view: View?,
        private val rocketId: String,
        private val coordinator: RocketDetailsContract.Coordinator
) : Presenter, LifecycleObserver, OnLoadRocketDetailsCallbacks {

    init {
        if (view is LifecycleOwner) {
            (view as LifecycleOwner).lifecycle.addObserver(this)
        }
    }

    override fun onCreateViewForFirstTime() {
        view!!.setUpViews()
    }

    override fun onViewRecreated(view: View) {
        this.view = view
    }

    @OnLifecycleEvent(Event.ON_START)
    override fun onStart() {
        view!!.showAsLoading()
        coordinator.getRocketDetailsUIM(rocketId, this)
    }

    override fun onSuccessLoadingRocketDetails(rocketDetails: RocketDetailsUIM) {
        if (view != null) {
            view!!.showRocketDetails(rocketDetails)
        }
    }

    override fun onErrorLoadingRocketDetails() {
        if (view != null) {
            view!!.showAsErrorLoading()
        }
    }

    override fun onLaunchClick(videoKey: String) {
        view!!.openVideoInYoutubeApp(videoKey)
    }

    @OnLifecycleEvent(Event.ON_STOP)
    override fun onStop() {
        coordinator.cancel()
    }

    override fun onDestroyView() {
        view = null
    }

    @OnLifecycleEvent(Event.ON_DESTROY)
    override fun onDestroy() {
        view = null
    }
}
