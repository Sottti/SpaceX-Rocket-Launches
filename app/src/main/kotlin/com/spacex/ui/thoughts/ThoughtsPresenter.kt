package com.spacex.ui.thoughts

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent

internal class ThoughtsPresenter(private val view: ThoughtsContract.View) : ThoughtsContract.Presenter, LifecycleObserver {
    init {
        if (view is LifecycleOwner) {
            (view as LifecycleOwner).lifecycle.addObserver(this)
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    override fun onStart() {
        view.setUpViews();
    }

    override fun onUpNavigation() {
        view.navigateUp()
    }
}
