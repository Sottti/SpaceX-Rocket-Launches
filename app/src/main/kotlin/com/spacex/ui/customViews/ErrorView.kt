package com.spacex.ui.customViews

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import com.spacex.ui.R
import com.spacex.ui.databinding.ErrorViewBinding

class ErrorView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0) : ConstraintLayout(context, attrs, defStyleAttr) {

    private var onRefreshListener: OnRefreshListener? = null

    init {
        init(context)
    }

    private fun init(context: Context) {
        val viewBinding = DataBindingUtil.inflate<ErrorViewBinding>(
                LayoutInflater.from(context), R.layout.error_view, this, true)
        viewBinding.retry.setOnClickListener {
            if (onRefreshListener != null) {
                onRefreshListener!!.onRefresh()
            }
        }
    }

    fun setOnRetryListener(onRefreshListener: OnRefreshListener) {
        this.onRefreshListener = onRefreshListener
    }

    interface OnRefreshListener {
        fun onRefresh()
    }
}
