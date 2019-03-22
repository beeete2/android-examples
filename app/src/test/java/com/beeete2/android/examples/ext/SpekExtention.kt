package com.beeete2.android.examples.ext

import androidx.arch.core.executor.TaskExecutor
import io.mockk.MockKVerificationScope
import io.mockk.verify
import org.spekframework.spek2.dsl.GroupBody

/**
 * Taken from https://github.com/moriiimo/UnitTestWithSpek/blob/master/app/src/test/kotlin/SpekExtention.kt
 */
fun GroupBody.useLiveData() {
    androidx.arch.core.executor.ArchTaskExecutor.getInstance().setDelegate(object : TaskExecutor() {
        override fun executeOnDiskIO(runnable: Runnable) {
            runnable.run()
        }

        override fun isMainThread(): Boolean {
            return true
        }

        override fun postToMainThread(runnable: Runnable) {
            runnable.run()
        }
    })
}
