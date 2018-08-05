package com.spacex.ui.di

import com.spacex.ui.about.AboutActivity
import com.spacex.ui.about.AboutModule
import com.spacex.ui.rocketDetails.RocketDetailsFragment
import com.spacex.ui.rocketDetails.RocketDetailsModule
import com.spacex.ui.rocketList.RocketListActivity
import com.spacex.ui.rocketList.RocketListModule
import com.spacex.ui.rockets.RocketsActivity
import com.spacex.ui.rockets.RocketsModule
import com.spacex.ui.thoughts.ThoughtsActivity
import com.spacex.ui.thoughts.ThoughtsModule
import com.spacex.ui.welcome.WelcomeActivity
import com.spacex.ui.welcome.WelcomeModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [RocketListModule::class])
    internal abstract fun bindRocketListActivity(): RocketListActivity

    @ContributesAndroidInjector(modules = [RocketsModule::class])
    internal abstract fun bindRocketsActivity(): RocketsActivity

    @ContributesAndroidInjector(modules = [RocketDetailsModule::class])
    internal abstract fun bindRocketFragment(): RocketDetailsFragment

    @ContributesAndroidInjector(modules = [WelcomeModule::class])
    internal abstract fun bindWelcomeActivity(): WelcomeActivity

    @ContributesAndroidInjector(modules = [ThoughtsModule::class])
    internal abstract fun bindThoughtsActivity(): ThoughtsActivity

    @ContributesAndroidInjector(modules = [AboutModule::class])
    internal abstract fun bindAboutActivity(): AboutActivity
}
