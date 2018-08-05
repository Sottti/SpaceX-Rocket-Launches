package com.spacex.ui.rockets

internal class RocketsContract {

    interface View {

        fun setUpViews()

        fun navigateUp()
    }

    interface Presenter {

        fun onCreate()

        fun onUpNavigation()
    }
}
