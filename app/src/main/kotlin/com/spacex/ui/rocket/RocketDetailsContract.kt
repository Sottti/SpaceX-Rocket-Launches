package com.spacex.ui.rocket

internal object RocketDetailsContract {

    val ARGUMENT_ROCKET_ID = "rocketId"
    val NO_ARGUMENT = -1

    internal interface View {

        fun setUpViews(rocketId: Int)
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

        interface OnLoadRocketDetailsCallbacks {

            fun onSuccessLoadingRocketDetails()

            fun onErrorLoadingRocketDetails()
        }
    }
}
