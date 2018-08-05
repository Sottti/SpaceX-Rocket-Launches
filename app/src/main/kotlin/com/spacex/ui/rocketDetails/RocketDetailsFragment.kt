package com.spacex.ui.rocketDetails

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.spacex.ui.R
import com.spacex.ui.databinding.RocketDetailsBinding
import com.spacex.ui.rocketDetails.launches.RocketLaunchVH
import com.spacex.ui.rocketDetails.launches.RocketLaunchesAdapter
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class RocketDetailsFragment : DaggerFragment(), RocketDetailsContract.View, RocketLaunchVH.OnRocketLaunchClickListener {

    var presenter: RocketDetailsContract.Presenter? = null
        @Inject set
    private var viewBinding: RocketDetailsBinding? = null
    private var adapter: RocketLaunchesAdapter? = null
    private var myContext: Context? = null

    companion object {
        fun newInstance(rocketId: String): RocketDetailsFragment {
            val bundle = Bundle()
            bundle.putString(RocketDetailsContract.ARGUMENT_ROCKET_ID, rocketId)
            val rocketDetailsFragment = RocketDetailsFragment()
            rocketDetailsFragment.arguments = bundle
            return rocketDetailsFragment
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        this.myContext = context
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?): View? {
        if (viewBinding == null) {
            viewBinding = DataBindingUtil.inflate(inflater, R.layout.rocket_details, container, false)
            presenter!!.onCreateViewForFirstTime()
        } else {
            presenter!!.onViewRecreated(this)
        }
        return viewBinding!!.root
    }

    override fun setUpViews() {
        viewBinding!!.recyclerView.setHasFixedSize(true)
    }

    override fun showRocketDetails(rocketDetails: RocketDetailsUIM) {
        viewBinding!!.includeProgressBar.progressBar.visibility = View.GONE
        viewBinding!!.content.visibility = View.VISIBLE
        viewBinding!!.errorView.visibility = View.GONE
        viewBinding!!.emptyView.visibility = View.GONE
        viewBinding!!.uim = RocketDetailsUIMDecorator(myContext!!, rocketDetails)
        if (viewBinding!!.recyclerView.adapter == null) {
            adapter = RocketLaunchesAdapter(rocketDetails.launches, this)
            viewBinding!!.recyclerView.adapter = adapter
        } else {
            adapter!!.refreshData(rocketDetails.launches)
        }
        viewBinding!!.chart.addSeries(rocketDetails.chartSeries)
    }

    override fun onClick(videoKey: String) {
        presenter!!.onLaunchClick(videoKey)
    }

    override fun showAsEmpty() {
        viewBinding!!.includeProgressBar.progressBar.visibility = View.GONE
        viewBinding!!.emptyView.visibility = View.VISIBLE
        viewBinding!!.errorView.visibility = View.GONE
        viewBinding!!.content.visibility = View.GONE
    }

    override fun showAsErrorLoading() {
        viewBinding!!.includeProgressBar.progressBar.visibility = View.GONE
        viewBinding!!.errorView.visibility = View.VISIBLE
        viewBinding!!.emptyView.visibility = View.GONE
        viewBinding!!.content.visibility = View.GONE
    }

    override fun showAsLoading() {
        viewBinding!!.includeProgressBar.progressBar.visibility = View.VISIBLE
        viewBinding!!.errorView.visibility = View.GONE
        viewBinding!!.emptyView.visibility = View.GONE
        viewBinding!!.content.visibility = View.GONE
    }

    override fun openVideoInYoutubeApp(videoKey: String) {
        val uri = "http://www.youtube.com/watch?v=$videoKey"
        myContext!!.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(uri)))
    }
}
