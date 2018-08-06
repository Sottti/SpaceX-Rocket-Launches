package com.spacex.ui.rocketDetails

import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import com.spacex.ui.rocketDetails.launches.RocketLaunchItemUIM

interface RocketDetailsContract {

    interface View {

        fun setUpViews()

        fun showRocketDetails(rocketDetails: RocketDetailsUIM)

        fun navigateToVideo(videoKey: String)

        fun showAsLoading()

        fun showAsErrorLoading()

        fun showAsEmpty()

        fun showChart(chartSeries: LineGraphSeries<DataPoint?>)

        fun hideChart()

        fun showLaunches(launches: List<RocketLaunchItemUIM>)

        fun hideLaunches()
    }

    interface Presenter {

        fun onCreateViewForFirstTime()

        fun onViewRecreated(view: RocketDetailsContract.View)

        fun onStart()

        fun onRetry()

        fun onLaunchClick(videoKey: String)

        fun onStop()

        fun onDestroyView()
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
