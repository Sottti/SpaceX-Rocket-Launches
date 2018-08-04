package com.spacex.ui.rockets

internal class RocketsContract {

    internal interface View {

        fun setUpViews()

        fun navigateUp()
    }

    internal interface Presenter {

        fun onCreate()

        fun onUpNavigation()
    }
}
