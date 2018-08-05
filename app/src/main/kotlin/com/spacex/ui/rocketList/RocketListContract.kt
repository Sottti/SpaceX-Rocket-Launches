package com.spacex.ui.rocketList

interface RocketListContract {

    interface View {

        fun setUpViews()

        fun showAsLoading()

        fun showAsEmpty()

        fun showAsErrorLoading()

        fun showRockets(rockets: List<RocketUIM>)

        fun showAllRocketsFilterAsSelected()

        fun showActiveRocketsFilterAsSelected()

        fun navigateToRocketDetails(rocketId: String, rocketIds: ArrayList<String>?)

        fun navigateToWelcomeActivity()

        fun navigateToThoughtsActivity()

        fun navigateToAboutActivity()

        fun showFilterOptions()

        fun hideFilterOptions()

        fun navigateBack()
    }

    interface Presenter {

        fun onCreate()

        fun onStart()

        fun onRocketClicked(rocketId: String)

        fun onFilterRocketsClick()

        fun onShowAllRocketsFilterOptionClick()

        fun onShowActiveRocketsFilterOptionClick()

        fun onCloseFilterOptions()

        fun onShowWelcomeClick()

        fun onThoughtsClick()

        fun onAboutWelcomeClick()

        fun onRefresh()

        fun onRetry()

        fun onBackNavigationPressed()

        fun onStop()
        fun onDestroy()
    }

    interface Coordinator {
        fun loadAllRockets(callbacks: OnLoadRocketListCallbacks)

        fun loadActiveRockets(callbacks: OnLoadRocketListCallbacks)

        fun cancel()

        interface OnLoadRocketListCallbacks {
            fun onSuccessLoadingRocketList(rockets: List<RocketUIM>)

            fun onErrorLoadingRocketList()
        }
    }
}
