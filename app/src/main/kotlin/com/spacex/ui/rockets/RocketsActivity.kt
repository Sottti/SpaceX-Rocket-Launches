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
import com.spacex.ui.rockets.RocketsContract.ROCKET_IDS
import com.spacex.ui.rockets.RocketsContract.SELECTED_ROCKET_ID
import javax.inject.Inject

class RocketsActivity : DaggerAppCompatActivityBase(), RocketsContract.View {

    companion object {

        fun startActivity(context: Context, rocketIds: ArrayList<String>?, selectedRocketId: String) {
            val bundle = Bundle()
            bundle.putString(SELECTED_ROCKET_ID, selectedRocketId)
            bundle.putStringArrayList(ROCKET_IDS, rocketIds)
            val intent = Intent(context, RocketsActivity::class.java)
            intent.putExtras(bundle)
            context.startActivity(intent)
        }
    }

    var presenter: RocketsContract.Presenter? = null
        @Inject set
    private var viewBinding: RocketsBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = DataBindingUtil.setContentView(this, R.layout.rockets)
    }

    override fun setUpViews(selectedRocketId: String, rocketIds: ArrayList<String>) {
        setUpToolbar()
        setUpViewPager(selectedRocketId, rocketIds)
    }

    private fun setUpToolbar() {
        setSupportActionBar(viewBinding!!.includeToolbar.toolbar)
        if (supportActionBar != null) {
            supportActionBar!!.setDisplayShowTitleEnabled(false)
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun setUpViewPager(selectedRocketId: String, rocketIds: ArrayList<String>) {
        viewBinding!!.viewPager.adapter =
                RocketDetailsPagerAdapter(supportFragmentManager, rocketIds, selectedRocketId)
        viewBinding!!.viewPager.currentItem = rocketIds.indexOf(selectedRocketId)
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

    private inner class RocketDetailsPagerAdapter internal constructor(
            fragmentManager: FragmentManager,
            val rocketIds: ArrayList<String>,
            val selectedRocketId: String) : FragmentPagerAdapter(fragmentManager) {

        override fun getItem(position: Int): Fragment {
            return RocketDetailsFragment.newInstance(rocketIds[position])
        }

        override fun getCount(): Int {
            return rocketIds.size
        }
    }
}
