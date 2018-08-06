package com.spacex.ui.about

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.core.app.NavUtils
import androidx.databinding.DataBindingUtil
import com.spacex.ui.R
import com.spacex.ui.common.IntentUtils
import com.spacex.ui.common.isAtLeastLollipop
import com.spacex.ui.databinding.AboutBinding
import com.spacex.ui.di.DaggerAppCompatActivityBase
import javax.inject.Inject

class AboutActivity : DaggerAppCompatActivityBase(), AboutContract.View {

    private lateinit var viewBinding: AboutBinding
    @Inject
    lateinit var presenter: AboutContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = DataBindingUtil.setContentView(this, R.layout.about)
    }

    override fun setUpViews() {
        setUpToolbar()
        viewBinding.clickHandler = AboutActivityClickHandler()
        if (isAtLeastLollipop()) {
            val elevation = resources.getDimension(R.dimen.appbar_elevation)
            viewBinding.header.elevation = elevation
            viewBinding.image.elevation = elevation
        }
    }

    private fun setUpToolbar() {
        setSupportActionBar(viewBinding.includeToolbar.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                presenter.onUpNavigation()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun navigateUp() {
        NavUtils.navigateUpFromSameTask(this)
    }

    override fun navigateTo(url: String) {
        IntentUtils.loadChromeCustomTab(this, url)
    }

    @Suppress("UNUSED_PARAMETER")
    inner class AboutActivityClickHandler {

        fun onGithubClick(view: View) {
            presenter.onGithubClick()
        }

        fun onStackOverflowClick(view: View) {
            presenter.onStackOverflowClick()
        }

        fun onMediumClick(view: View) {
            presenter.onMediumClick()
        }

        fun onTwitterClick(view: View) {
            presenter.onTwitterClick()
        }

        fun onLinkedInClick(view: View) {
            presenter.onLinkedInClick()
        }
    }

    companion object {

        fun startActivity(context: Context) {
            context.startActivity(Intent(context, AboutActivity::class.java))
        }
    }
}
