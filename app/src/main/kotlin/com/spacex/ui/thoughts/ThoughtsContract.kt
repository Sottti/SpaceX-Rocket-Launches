package com.spacex.ui.thoughts

class ThoughtsContract {

    internal interface View {

        fun setUpViews()

        fun navigateUp()

        fun showThoughts(thoughts: List<ThoughtUIM>)

        fun navigateToVideo(videoKey: String)
    }

    interface Presenter {

        fun onStart()

        fun onVideoClick(videoKey: String)

        fun onUpNavigation()
    }
}
