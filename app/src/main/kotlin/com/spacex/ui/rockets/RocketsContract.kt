package com.spacex.ui.rockets

class RocketsContract {

    interface View {

        fun setUpViews()

        fun navigateUp()
    }

    interface Presenter {

        fun onCreate()

        fun onUpNavigation()
    }
}
