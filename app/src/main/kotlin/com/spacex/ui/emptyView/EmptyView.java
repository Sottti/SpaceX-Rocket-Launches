package com.spacex.ui.emptyView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import com.spacex.ui.R;

public class EmptyView extends ConstraintLayout {

  public EmptyView(@NonNull Context context) {
    this(context, null);
  }

  public EmptyView(@NonNull Context context, @Nullable AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public EmptyView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.empty_view, this, true);
  }
}
