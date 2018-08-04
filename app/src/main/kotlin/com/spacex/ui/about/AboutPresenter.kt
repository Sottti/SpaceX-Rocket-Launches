package com.spacex.ui.about

import com.spacex.ui.about.AboutContract.View

internal class AboutPresenter(private val view: View) : AboutContract.Presenter {

    override fun onUpNavigation() {
        view.navigateUp()
    }
}
