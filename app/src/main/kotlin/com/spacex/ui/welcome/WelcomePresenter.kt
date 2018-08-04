package com.spacex.ui.welcome

import androidx.lifecycle.Lifecycle.Event
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import com.spacex.ui.InteractionsHistory
import com.spacex.ui.welcome.WelcomeContract.Presenter
import com.spacex.ui.welcome.WelcomeContract.View

internal class WelcomePresenter(private val view: View) : Presenter, LifecycleObserver {

    init {
        if (view is LifecycleOwner) {
            (view as LifecycleOwner).lifecycle.addObserver(this)
        }
    }

    @OnLifecycleEvent(Event.ON_START)
    override fun onStart() {
        view.setUpViews()
    }

    override fun onButtonClick() {
        view.navigateToRocketList()
        InteractionsHistory.setWelcomeAsShown()
    }
}
