package com.spacex.ui.thoughts

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent

internal class ThoughtsPresenter(
        private val view: ThoughtsContract.View
) : ThoughtsContract.Presenter, LifecycleObserver {

    init {
        if (view is LifecycleOwner) {
            (view as LifecycleOwner).lifecycle.addObserver(this)
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    override fun onStart() {
        view.setUpViews();
        view.showThoughts(listOf(
                ThoughtUIM("Intro", "6/8/18", "ZOxkFh7O7FQ"),
                ThoughtUIM("Project Structure", "6/8/18", "ZOxkFh7O7FQ"),
                ThoughtUIM("Architecture/Design Patterns", "6/8/18", "ZOxkFh7O7FQ"),
                ThoughtUIM("Kotlin", "6/8/18", "ZOxkFh7O7FQ"),
                ThoughtUIM("Others", "6/8/18", "ZOxkFh7O7FQ")
        ))
    }

    override fun onUpNavigation() {
        view.navigateUp()
    }

    override fun onVideoClick(videoKey: String) {
        view.navigateToVideo(videoKey)
    }
}
