package com.spacex.ui.customViews

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.spacex.ui.R

internal class EmptyView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0) : ConstraintLayout(context, attrs, defStyleAttr) {

    init {
        DataBindingUtil.inflate<ViewDataBinding>(
                LayoutInflater.from(context), R.layout.empty_view, this, true)
    }
}
