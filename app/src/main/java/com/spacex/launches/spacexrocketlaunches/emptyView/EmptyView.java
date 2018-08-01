package com.spacex.launches.spacexrocketlaunches.emptyView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import com.spacex.launches.spacexrocketlaunches.R;
import com.spacex.launches.spacexrocketlaunches.databinding.EmptyViewBinding;

public class EmptyView extends ConstraintLayout {

  private EmptyViewBinding mViewBinding;

  public EmptyView(@NonNull Context context) {
    this(context, null);
  }

  public EmptyView(@NonNull Context context, @Nullable AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public EmptyView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    mViewBinding =
        DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.empty_view, this, true);
  }
}
