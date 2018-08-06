package com.spacex.ui.thoughts

interface ThoughtsContract {

    interface View {

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
