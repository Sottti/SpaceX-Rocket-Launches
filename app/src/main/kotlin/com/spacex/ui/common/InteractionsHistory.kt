package com.spacex.ui.common

import android.preference.PreferenceManager
import com.spacex.ui.base.RocketLaunchesApp

internal object InteractionsHistory {

    private const val wasWelcomeShown = "was-welcome-shown"
    private val sSharedPreferencesDefault = PreferenceManager.getDefaultSharedPreferences(RocketLaunchesApp.instance)

    fun wasWelcomeShown(): Boolean {
        return sSharedPreferencesDefault.getBoolean(wasWelcomeShown, false)
    }

    fun setWelcomeAsShown() {
        sSharedPreferencesDefault.edit().putBoolean(wasWelcomeShown, true).apply()
    }
}
