package com.beeete2.android.examples.ui.friends

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beeete2.android.examples.ext.distinctUntilChanged
import com.beeete2.android.examples.model.entity.Friend
import com.beeete2.android.examples.model.usecase.FriendUseCase
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

class FriendFullyViewModel @Inject constructor(
    private val friendUseCase: FriendUseCase
) : ViewModel() {

    private val _friends = MutableLiveData<List<Friend>>()
    val friends = _friends.distinctUntilChanged()

    init {
        viewModelScope.launch {
            val deferred = async { friendUseCase.findFriends() }
            _friends.value = deferred.await()
        }
    }
}
