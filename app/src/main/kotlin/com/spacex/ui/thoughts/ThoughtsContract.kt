package com.spacex.ui.thoughts

class ThoughtsContract {

    internal interface View {

        fun setUpViews()

        fun navigateUp()

        fun showThoughts(thoughts: List<ThoughtUIM>)
    }

    interface Presenter {

        fun onStart()

        fun onUpNavigation()
    }
}
