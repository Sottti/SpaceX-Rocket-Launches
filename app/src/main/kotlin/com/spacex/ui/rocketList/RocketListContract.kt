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

        fun navigateToRocketDetails(rocketId: Int)

        fun navigateToWelcomeActivity()

        fun navigateToAboutActivity()

        fun showFilterOptions()

        fun hideFilterOptions()

        fun navigateBack()
    }

    interface Presenter {

        fun onCreate()

        fun onStart()

        fun onRocketClicked(rocketId: Int)

        fun onFilterRocketsClick()

        fun onShowAllRocketsFilterOptionClick()

        fun onShowActiveRocketsFilterOptionClick()

        fun onCloseFilterOptions()

        fun onShowWelcomeClick()

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
