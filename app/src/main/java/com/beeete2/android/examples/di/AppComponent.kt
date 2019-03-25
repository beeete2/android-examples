package com.beeete2.android.examples.di

import android.app.Application
import com.beeete2.android.examples.App
import com.beeete2.android.examples.ui.epoxy.EpoxyActivity
import com.beeete2.android.examples.ui.friends.*
import com.beeete2.android.examples.ui.keypad.KeypadActivity
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        DatabaseModule::class,
        FriendSimplyActivity.FriendActivityModule::class,
        FriendFullyActivity.FriendFullyActivityModule::class,
        FriendBarrierActivity.FriendBarrierActivityModule::class,
        FriendMinHeightActivity.FriendMinHeightActivityModule::class,
        KeypadActivity.KeypadActivityModule::class,
        ConstraintChainSpreadActivity.ConstraintChainSpreadActivityModule::class,
        ConstraintEllipsizeActivity.ConstraintEllipsizeActivityActivityModule::class,
        EpoxyActivity.EpoxyActivityModule::class
    ]
)
interface AppComponent : AndroidInjector<App>  {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }

    override fun inject(app: App)
}
