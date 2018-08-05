package com.spacex.ui.welcome

interface WelcomeContract {

    interface View {
        fun setUpViews()

        fun navigateToRocketList()
    }

    interface Presenter {

        fun onStart()

        fun onButtonClick()
    }
}
