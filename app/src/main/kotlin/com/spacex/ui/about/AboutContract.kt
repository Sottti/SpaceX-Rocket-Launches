package com.spacex.ui.about

interface AboutContract {

    interface View {

        fun setUpViews()

        fun navigateTo(url: String)

        fun navigateUp()

    }

    interface Presenter {

        fun onStart()

        fun onUpNavigation()

        fun onGithubClick()

        fun onStackOverflowClick()

        fun onMediumClick()

        fun onTwitterClick()

        fun onLinkedInClick()
    }
}
