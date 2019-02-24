package com.beeete2.android.examples.di

import android.app.Application
import com.beeete2.android.examples.App
import com.beeete2.android.examples.ui.friends.FriendSimplyActivity
import com.beeete2.android.examples.ui.friends.FriendBarrierActivity
import com.beeete2.android.examples.ui.friends.FriendFullyActivity
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
        FriendBarrierActivity.FriendBarrierActivityModule::class
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
