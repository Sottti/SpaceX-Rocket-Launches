package com.spacex.ui.about

import androidx.annotation.NonNull

class AboutContract {

    internal interface View {

        fun setUpViews()

        fun navigateTo(@NonNull url: String)

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
