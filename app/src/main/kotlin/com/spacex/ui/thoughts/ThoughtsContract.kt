package com.spacex.ui.thoughts

class ThoughtsContract {

    internal interface View {

        fun setUpViews()

        fun navigateUp()

    }

    interface Presenter {

        fun onStart()

        fun onUpNavigation()
    }
}
