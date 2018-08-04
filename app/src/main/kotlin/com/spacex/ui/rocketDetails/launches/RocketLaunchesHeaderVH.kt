package com.spacex.ui.rocketDetails.launches

import com.spacex.ui.databinding.RocketLaunchesHeaderVhBinding

internal class RocketLaunchesHeaderVH(
        private val mViewBinding: RocketLaunchesHeaderVhBinding
) : RocketLaunchesItemVH(mViewBinding.root) {

    fun onBind(rocketLaunchesHeaderUIM: RocketLaunchHeaderUIM) {
        mViewBinding.uim = RocketLaunchesHeaderUIMDecorator(rocketLaunchesHeaderUIM)
        mViewBinding.executePendingBindings()
    }
}
