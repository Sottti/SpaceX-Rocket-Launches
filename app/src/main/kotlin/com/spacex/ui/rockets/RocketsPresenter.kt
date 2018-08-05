package com.spacex.ui.rockets

import androidx.lifecycle.Lifecycle.Event
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import com.spacex.ui.rockets.RocketsContract.Presenter
import com.spacex.ui.rockets.RocketsContract.View

internal class RocketsPresenter internal constructor(private val view: View) : Presenter, LifecycleObserver {

    init {
        if (view is LifecycleOwner) {
            (view as LifecycleOwner).lifecycle.addObserver(this)
        }
    }

    @OnLifecycleEvent(Event.ON_CREATE)
    override fun onCreate() {
        view.setUpViews()
    }

    override fun onUpNavigation() {
        view.navigateUp()
    }
}
