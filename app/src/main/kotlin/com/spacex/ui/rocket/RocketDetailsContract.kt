package com.spacex.ui.rocket

internal object RocketDetailsContract {

    const val NO_ARGUMENT = -1
    const val ARGUMENT_ROCKET_ID = "rocketId"

    internal interface View {

        fun setUpViews(rocketId: Int)

        fun showAsErrorLoadingRocketDetails()

        fun showRocketDetails(rocketDetails: RocketDetailsUIM)
    }

    internal interface Presenter {

        fun onCreateViewForFirstTime()

        fun onViewRecreated(view: RocketDetailsContract.View)

        fun onStart()

        fun onStop()

        fun onDestroyView()

        fun onDestroy()
    }

    internal interface Coordinator {
        fun getRocketDetailsUIM(callbacks: OnLoadRocketDetailsCallbacks)

        fun cancel()

        interface OnLoadRocketDetailsCallbacks {

            fun onSuccessLoadingRocketDetails(rocketDetails : RocketDetailsUIM)

            fun onErrorLoadingRocketDetails()
        }
    }
}
