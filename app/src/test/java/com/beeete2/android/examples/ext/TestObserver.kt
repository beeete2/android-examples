package com.beeete2.android.examples.ext

import androidx.lifecycle.Observer
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

/**
 * Taken from https://medium.com/@star_zero/livedata%E3%81%AEunittest-2b295d2818c1
 */
class TestObserver<T>(count: Int = 1) : Observer<T> {

    private val latch: CountDownLatch = CountDownLatch(count)

    private val values = mutableListOf<T?>()

    override fun onChanged(t: T) {
        values.add(t)
        latch.countDown()
    }

    fun get(): T = nullableGet()!!

    private fun nullableGet(): T? {
        if (values.size == 0) {
            throw IllegalStateException("onChanged is not called.")
        }
        return values[values.size - 1]
    }

    fun await(timeout: Long = 1, unit: TimeUnit = TimeUnit.SECONDS) {
        if (!latch.await(timeout, unit)) {
            throw TimeoutException()
        }
    }

}