package com.beeete2.android.examples.model.usecase

import com.beeete2.android.examples.model.entity.Friend
import com.beeete2.android.examples.model.entity.Gender
import com.beeete2.android.examples.model.repository.FriendRepository
import kotlinx.coroutines.Deferred
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FriendUseCase @Inject constructor(
    private val friendRepository: FriendRepository
) {

    suspend fun findFriends(): List<Friend> {
        return friendRepository.friends
    }

}
