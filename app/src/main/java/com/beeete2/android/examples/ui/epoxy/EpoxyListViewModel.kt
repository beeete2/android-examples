package com.beeete2.android.examples.ui.epoxy

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beeete2.android.examples.ext.distinctUntilChanged
import com.beeete2.android.examples.model.entity.Friend
import com.beeete2.android.examples.model.usecase.FriendUseCase
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class EpoxyListViewModel @Inject constructor(
    private val friendUseCase: FriendUseCase
) : ViewModel() {

    private val _friends = MutableLiveData<FetchResult<List<Friend>>>(FetchResult.Unloaded)
    val friend = _friends.distinctUntilChanged()

    fun fetch() {
        _friends.value = FetchResult.Loading
        viewModelScope.launch {
            val deferred = async {
                delay(3000)
                FetchResult.Success(friendUseCase.findFriends())
            }
            _friends.value = deferred.await()
        }
    }

    fun fetchEmpty() {
        _friends.value = FetchResult.Success(emptyList())
    }

    fun fetchError() {
        _friends.value = FetchResult.Failure(Exception("fetchError"))
    }

}
