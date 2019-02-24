package com.beeete2.android.examples

import com.beeete2.android.examples.di.DaggerAppComponent
import dagger.android.support.DaggerApplication

class App : DaggerApplication() {

    override fun applicationInjector() = DaggerAppComponent.builder()
        .application(this)
        .build()

}