package com.spacex.ui.rocketList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.spacex.ui.databinding.RocketVhBinding
import com.spacex.ui.rocketList.RocketVH.OnRocketClickListener

internal class RocketListAdapter(
        private var rockets: List<RocketUIM>?,
        private val onRocketClickListener: OnRocketClickListener) : RecyclerView.Adapter<RocketVH>() {

    fun refreshData(rockets: List<RocketUIM>) {
        DiffUtil.calculateDiff(RocketsDiffCallback(this.rockets!!, rockets)).dispatchUpdatesTo(this)
        this.rockets = rockets
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RocketVH {
        return RocketVH(
                RocketVhBinding.inflate(LayoutInflater.from(parent.context), parent, false),
                onRocketClickListener)
    }

    override fun onBindViewHolder(holder: RocketVH, position: Int) {
        holder.onBind(rockets!![position])
    }

    override fun getItemCount(): Int {
        return rockets!!.size
    }
}
