package com.beeete2.android.examples.ext

import androidx.lifecycle.*

inline fun <T> LiveData<T>.observeNonNull(
    owner: LifecycleOwner,
    crossinline observer: (T) -> Unit
) {
    this.observe(owner, Observer {
        if (it != null) {
            observer(it)
        }
    })
}

fun <T> LiveData<T>.distinctUntilChanged(): LiveData<T> = Transformations.distinctUntilChanged(this)

inline fun <X, Y> LiveData<X>.map(crossinline transformer: (X) -> Y): LiveData<Y> =
    Transformations.map(this) { transformer(it) }
