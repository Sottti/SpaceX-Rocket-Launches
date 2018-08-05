package com.spacex.ui.rockets

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.core.app.NavUtils
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.spacex.ui.R
import com.spacex.ui.databinding.RocketsBinding
import com.spacex.ui.di.DaggerAppCompatActivityBase
import com.spacex.ui.rocketDetails.RocketDetailsFragment
import javax.inject.Inject

class RocketsActivity : DaggerAppCompatActivityBase(), RocketsContract.View {

    var presenter: RocketsContract.Presenter? = null
        @Inject set
    private var viewBinding: RocketsBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = DataBindingUtil.setContentView(this, R.layout.rockets)
    }

    override fun setUpViews() {
        setUpToolbar()
        setUpViewPager()
    }

    private fun setUpToolbar() {
        setSupportActionBar(viewBinding!!.includeToolbar.toolbar)
        if (supportActionBar != null) {
            supportActionBar!!.setDisplayShowTitleEnabled(false)
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun setUpViewPager() {
        viewBinding!!.viewPager.adapter = RocketDetailsPagerAdapter(supportFragmentManager)
    }

    private fun getRocketStringId(i: Int): String {
        return when (i) {
            1 -> "falcon1"
            2 -> "falcon9"
            3 -> "falconheavy"
            4 -> "bfr"
            else -> ""
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                presenter!!.onUpNavigation()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun navigateUp() {
        NavUtils.navigateUpFromSameTask(this)
    }

    private inner class RocketDetailsPagerAdapter internal constructor(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {

        override fun getItem(position: Int): Fragment {
            return RocketDetailsFragment.newInstance(getRocketStringId(position + 1))
        }

        override fun getCount(): Int {
            return 4
        }
    }

    companion object {

        fun startActivity(context: Context) {
            context.startActivity(Intent(context, RocketsActivity::class.java))
        }
    }
}
