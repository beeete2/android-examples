package com.beeete2.android.examples.ui.epoxy

sealed class FetchResult<out T> {
    object Unloaded : FetchResult<Nothing>()
    object Loading : FetchResult<Nothing>()
    data class Success<out T>(val result: T) : FetchResult<T>()
    data class Failure<out T>(val throwable: Throwable) : FetchResult<T>()
}
