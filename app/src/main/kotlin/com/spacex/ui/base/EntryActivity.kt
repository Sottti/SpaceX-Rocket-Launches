package com.spacex.ui.base

import android.app.Activity
import com.spacex.ui.common.InteractionsHistory
import com.spacex.ui.rocketList.RocketListActivity
import com.spacex.ui.welcome.WelcomeActivity

internal class EntryActivity : Activity() {

    override fun onStart() {
        super.onStart()
        showFirstVisibleActivity()
    }

    private fun showFirstVisibleActivity() {
        if (InteractionsHistory.wasWelcomeShown()) {
            RocketListActivity.startActivity(this)
        } else {
            WelcomeActivity.startActivity(this)
        }
    }
}
