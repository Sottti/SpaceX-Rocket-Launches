package com.spacex.ui.rockets

object RocketsContract {

    const val ROCKET_IDS = "rocketIds"
    const val SELECTED_ROCKET_ID = "selectedRocketId"

    interface View {

        fun setUpViews(selectedRocketId: String, rocketIds: ArrayList<String>)

        fun navigateUp()
    }

    interface Presenter {

        fun onCreate()

        fun onUpNavigation()
    }
}
