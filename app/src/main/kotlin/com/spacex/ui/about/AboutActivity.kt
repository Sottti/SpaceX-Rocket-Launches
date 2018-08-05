package com.spacex.ui.about

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.annotation.NonNull
import androidx.core.app.NavUtils
import androidx.databinding.DataBindingUtil
import com.spacex.ui.IntentUtils
import com.spacex.ui.R
import com.spacex.ui.databinding.AboutBinding
import com.spacex.ui.di.DaggerAppCompatActivityBase
import com.spacex.ui.isAtLeastLollipop
import javax.inject.Inject

class AboutActivity : DaggerAppCompatActivityBase(), AboutContract.View {

    private var viewBinding: AboutBinding? = null
    var presenter: AboutContract.Presenter? = null
        @Inject set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = DataBindingUtil.setContentView(this, R.layout.about)
    }

    override fun setUpViews() {
        setUpToolbar()
        viewBinding!!.clickHandler = AboutActivityClickHandler()
        if (isAtLeastLollipop()) {
            viewBinding!!.header.elevation = resources.getDimension(R.dimen.appbar_elevation)
            viewBinding!!.image.elevation = resources.getDimension(R.dimen.appbar_elevation)
        }
    }

    private fun setUpToolbar() {
        setSupportActionBar(viewBinding!!.includeToolbar.toolbar)
        if (supportActionBar != null) {
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
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

    override fun navigateTo(@NonNull url: String) {
        IntentUtils.loadChromeCustomTab(this, url)
    }

    inner class AboutActivityClickHandler {

        fun onGithubClick(@NonNull view: View) {
            presenter!!.onGithubClick()
        }

        fun onStackOverflowClick(@NonNull view: View) {
            presenter!!.onStackOverflowClick()
        }

        fun onMediumClick(@NonNull view: View) {
            presenter!!.onMediumClick()
        }

        fun onTwitterClick(@NonNull view: View) {
            presenter!!.onTwitterClick()
        }

        fun onLinkedInClick(@NonNull view: View) {
            presenter!!.onLinkedInClick()
        }
    }

    companion object {

        fun startActivity(@NonNull context: Context) {
            context.startActivity(Intent(context, AboutActivity::class.java))
        }
    }
}
