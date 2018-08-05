package com.spacex.ui.thoughts

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.annotation.NonNull
import androidx.core.app.NavUtils
import androidx.databinding.DataBindingUtil
import com.spacex.ui.R
import com.spacex.ui.databinding.ThoughtsBinding
import com.spacex.ui.di.DaggerAppCompatActivityBase
import com.spacex.ui.rocketList.RocketListAdapter
import javax.inject.Inject

class ThoughtsActivity : DaggerAppCompatActivityBase(), ThoughtsContract.View, ThoughtsVH.OnThoughtClickListener {

    private var adapter: ThoughtsAdapter? = null
    private var viewBinding: ThoughtsBinding? = null
    var presenter: ThoughtsContract.Presenter? = null
        @Inject set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = DataBindingUtil.setContentView(this, R.layout.thoughts)
    }

    override fun setUpViews() {
        setUpToolbar()
    }

    private fun setUpToolbar() {
        setSupportActionBar(viewBinding!!.includeToolbar.toolbar)
        if (supportActionBar != null) {
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        }
    }

    override fun showThoughts(thoughts: List<ThoughtUIM>) {
        if (viewBinding!!.toughts.adapter == null) {
            adapter = ThoughtsAdapter(thoughts, this)
            viewBinding!!.toughts.adapter = adapter
        } else {
            adapter!!.refreshData(thoughts)
        }
    }

    override fun onClick(videoKey: String) {

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

    companion object {

        fun startActivity(@NonNull context: Context) {
            context.startActivity(Intent(context, ThoughtsActivity::class.java))
        }
    }


}