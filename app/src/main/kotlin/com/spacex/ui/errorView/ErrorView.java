package com.spacex.ui.errorView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import com.spacex.ui.R;
import com.spacex.ui.databinding.ErrorViewBinding;

public class ErrorView extends ConstraintLayout {

  private OnRefreshListener onRefreshListener;

  public ErrorView(@NonNull Context context) {
    this(context, null);
  }

  public ErrorView(@NonNull Context context, @Nullable AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public ErrorView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init(context, attrs);
  }

  private void init(@NonNull Context context, @Nullable AttributeSet attributeSet) {
    final ErrorViewBinding viewBinding =
        DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.error_view, this, true);
    viewBinding.retry.setOnClickListener(
        view -> {
          if (onRefreshListener != null) {
            onRefreshListener.onRefresh();
          }
        });
  }

  public void setOnRetryListener(@NonNull OnRefreshListener onRefreshListener) {
    this.onRefreshListener = onRefreshListener;
  }

  public interface OnRefreshListener {

    void onRefresh();
  }
}
