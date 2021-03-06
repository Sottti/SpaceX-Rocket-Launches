package com.spacex.ui.welcome

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.spacex.ui.R
import com.spacex.ui.databinding.WelcomeBinding
import com.spacex.ui.di.DaggerAppCompatActivityBase
import com.spacex.ui.rocketList.RocketListActivity
import javax.inject.Inject

class WelcomeActivity : DaggerAppCompatActivityBase(), WelcomeContract.View {

    private lateinit var viewBinding: WelcomeBinding
    @Inject
    lateinit var presenter: WelcomeContract.Presenter

    companion object {
        fun startActivity(context: Context) {
            context.startActivity(Intent(context, WelcomeActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = DataBindingUtil.setContentView(this, R.layout.welcome)
    }

    override fun setUpViews() {
        viewBinding.button.setOnClickListener { presenter.onButtonClick() }
    }

    override fun navigateToRocketList() {
        RocketListActivity.startActivity(this)
    }
}
