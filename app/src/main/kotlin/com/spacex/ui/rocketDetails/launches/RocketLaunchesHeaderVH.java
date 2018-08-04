package com.spacex.ui.rocketDetails.launches;

import androidx.annotation.NonNull;
import com.spacex.ui.databinding.RocketLaunchesHeaderVhBinding;

class RocketLaunchesHeaderVH extends RocketLaunchesItemVH {

  private final RocketLaunchesHeaderVhBinding mViewBinding;

  RocketLaunchesHeaderVH(@NonNull final RocketLaunchesHeaderVhBinding binding) {
    super(binding.getRoot());
    mViewBinding = binding;
  }

  void onBind(@NonNull final RocketLaunchHeaderUIM rocketLaunchesHeaderUIM) {
    mViewBinding.setUIM(new RocketLaunchesHeaderUIMDecorator(rocketLaunchesHeaderUIM));
    mViewBinding.executePendingBindings();
  }
}
