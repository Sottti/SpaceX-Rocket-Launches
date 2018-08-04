package com.spacex.ui.about

internal class AboutContract {

    internal interface View {

        fun navigateUp()
    }

    internal interface Presenter {

        fun onUpNavigation()
    }
}
