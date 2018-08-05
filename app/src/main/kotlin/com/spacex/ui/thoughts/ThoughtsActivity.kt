package com.spacex.ui.thoughts

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.annotation.NonNull
import androidx.core.app.NavUtils
import androidx.databinding.DataBindingUtil
import com.spacex.ui.IntentUtils
import com.spacex.ui.R
import com.spacex.ui.databinding.ThoughtsBinding
import com.spacex.ui.di.DaggerAppCompatActivityBase
import javax.inject.Inject

class ThoughtsActivity : DaggerAppCompatActivityBase(), ThoughtsContract.View {

    private var adapter: ThoughtsAdapter? = null
    private lateinit var viewBinding: ThoughtsBinding
    @Inject
    lateinit var presenter: ThoughtsContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = DataBindingUtil.setContentView(this, R.layout.thoughts)
    }

    override fun setUpViews() {
        setUpToolbar()
    }

    private fun setUpToolbar() {
        setSupportActionBar(viewBinding.includeToolbar.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun showThoughts(thoughts: List<ThoughtUIM>) {
        if (viewBinding.thoughts.adapter == null) {
            adapter = ThoughtsAdapter(thoughts, object : ThoughtsVH.OnThoughtClickListener {
                override fun onClick(videoKey: String) {
                    presenter.onVideoClick(videoKey)
                }

            })
            viewBinding.thoughts.adapter = adapter
        } else {
            adapter?.refreshData(thoughts)
        }
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

    override fun navigateToVideo(videoKey: String) {
        IntentUtils.openVideoInYoutubeApp(this, videoKey)
    }

    companion object {

        fun startActivity(@NonNull context: Context) {
            context.startActivity(Intent(context, ThoughtsActivity::class.java))
        }
    }


}