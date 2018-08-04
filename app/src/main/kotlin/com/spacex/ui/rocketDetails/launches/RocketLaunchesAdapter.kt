package com.spacex.ui.rocketDetails.launches

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.spacex.ui.R
import com.spacex.ui.databinding.RocketLaunchVhBinding
import com.spacex.ui.databinding.RocketLaunchesHeaderVhBinding
import com.spacex.ui.rocketDetails.launches.RocketLaunchVH.OnRocketLaunchClickListener

internal class RocketLaunchesAdapter(
        private var launches: List<RocketLaunchItemUIM>?,
        private val onRocketLaunchClickListener: OnRocketLaunchClickListener) : RecyclerView.Adapter<RocketLaunchesItemVH>() {

    fun refreshData(launches: List<RocketLaunchItemUIM>) {
        DiffUtil.calculateDiff(RocketLaunchesDiffCallback(this.launches!!, launches))
                .dispatchUpdatesTo(this)
        this.launches = launches
    }

    override fun onCreateViewHolder(
            parent: ViewGroup, viewType: Int): RocketLaunchesItemVH {
        when (viewType) {
            R.layout.rocket_launches_header_vh -> return RocketLaunchesHeaderVH(
                    RocketLaunchesHeaderVhBinding.inflate(
                            LayoutInflater.from(parent.context), parent, false))
            R.layout.rocket_launch_vh -> return RocketLaunchVH(
                    RocketLaunchVhBinding.inflate(LayoutInflater.from(parent.context), parent, false),
                    onRocketLaunchClickListener)
        }
        throw IllegalArgumentException()
    }

    override fun onBindViewHolder(holder: RocketLaunchesItemVH, position: Int) {
        if (holder.itemViewType == R.layout.rocket_launches_header_vh) {
            (holder as RocketLaunchesHeaderVH).onBind(launches!![position] as RocketLaunchHeaderUIM)
        } else {
            (holder as RocketLaunchVH).onBind(launches!![position] as RocketLaunchUIM)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (launches!![position] is RocketLaunchHeaderUIM) {
            R.layout.rocket_launches_header_vh
        } else {
            R.layout.rocket_launch_vh
        }
    }

    override fun getItemCount(): Int {
        return launches!!.size
    }
}
