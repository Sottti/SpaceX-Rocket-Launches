package com.spacex.ui.rocketDetails

object RocketDetailsContract {

    const val NO_ARGUMENT = ""
    const val ARGUMENT_ROCKET_ID = "rocketId"

    interface View {

        fun setUpViews()

        fun showRocketDetails(rocketDetails: RocketDetailsUIM)

        fun openVideoInYoutubeApp(videoKey: String)

        fun showAsEmpty()

        fun showAsErrorLoading()
        fun showAsLoading()
    }

    interface Presenter {

        fun onCreateViewForFirstTime()

        fun onViewRecreated(view: RocketDetailsContract.View)

        fun onStart()

        fun onLaunchClick(videoKey: String)

        fun onStop()

        fun onDestroyView()

        fun onDestroy()
    }

    interface Coordinator {
        fun getRocketDetailsUIM(rocketId: String, callbacks: OnLoadRocketDetailsCallbacks)

        fun cancel()

        interface OnLoadRocketDetailsCallbacks {

            fun onSuccessLoadingRocketDetails(rocketDetails: RocketDetailsUIM)

            fun onErrorLoadingRocketDetails()
        }
    }
}
