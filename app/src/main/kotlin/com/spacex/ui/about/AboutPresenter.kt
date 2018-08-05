package com.spacex.ui.about

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import com.spacex.ui.about.AboutContract.View

internal class AboutPresenter(private val view: View) : AboutContract.Presenter, LifecycleObserver {
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

    override fun onGithubClick() {
        view.navigateTo("https://github.com/Sottti")
    }

    override fun onStackOverflowClick() {
        view.navigateTo("https://stackoverflow.com/users/1177959/sotti")
    }

    override fun onMediumClick() {
        view.navigateTo("https://medium.com/@sotti")
    }

    override fun onTwitterClick() {
        view.navigateTo("https://twitter.com/Sotttti")
    }

    override fun onLinkedInClick() {
        view.navigateTo("https://uk.linkedin.com/in/sotti")
    }
}
