package com.spacex.ui.rocketDetails

internal object RocketDetailsContract {

    const val NO_ARGUMENT = ""
    const val ARGUMENT_ROCKET_ID = "rocketId"

    internal interface View {

        fun setUpViews(rocketId: String)

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
        fun getRocketDetailsUIM(rocketId: String, callbacks: OnLoadRocketDetailsCallbacks)

        fun cancel()

        interface OnLoadRocketDetailsCallbacks {

            fun onSuccessLoadingRocketDetails(rocketDetails: RocketDetailsUIM)

            fun onErrorLoadingRocketDetails()
        }
    }
}
