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
        view.setUpViews()
        view.showThoughts(listOf(
                ThoughtUIM("Hello", "6/8/18", "1LQaY0OY1Zo"),
                ThoughtUIM("Project Structure", "6/8/18", "IgPRC3hVXsI"),
                ThoughtUIM("Architecture/Design Patterns", "6/8/18", "Bi5J_g-FLDQ"),
                ThoughtUIM("Coordinator? What Coordinator?", "6/8/18", "jJOQ0xW9BsU"),
                ThoughtUIM("Kotlin and Me", "6/8/18", "LSWmFUsN9X8"),
                ThoughtUIM("DI with Dagger", "6/8/18", "IkyKsB-22E0"),
                ThoughtUIM("Thanks and Sorry", "6/8/18", "yWH0OOHtDdo")
        ))
    }

    override fun onUpNavigation() {
        view.navigateUp()
    }

    override fun onVideoClick(videoKey: String) {
        view.navigateToVideo(videoKey)
    }
}
