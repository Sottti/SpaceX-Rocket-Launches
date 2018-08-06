package com.spacex.ui.rockets

interface RocketsContract {

    interface View {

        fun setUpViews(selectedRocketId: String, rocketIds: ArrayList<String>)

        fun navigateUp()
    }

    interface Presenter {

        fun onCreate()

        fun onUpNavigation()
    }
}
