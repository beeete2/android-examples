package com.beeete2.android.examples.di

import com.beeete2.android.examples.model.repository.FriendLocalRepository
import com.beeete2.android.examples.model.repository.FriendRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideFriendRepository(): FriendRepository = FriendLocalRepository()

}
