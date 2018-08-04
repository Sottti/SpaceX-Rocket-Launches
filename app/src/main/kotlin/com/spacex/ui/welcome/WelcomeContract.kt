package com.spacex.ui.welcome

internal interface WelcomeContract {

    interface View {
        fun setUpViews()

        fun navigateToRocketList()
    }

    interface Presenter {

        fun onStart()

        fun onButtonClick()
    }
}
