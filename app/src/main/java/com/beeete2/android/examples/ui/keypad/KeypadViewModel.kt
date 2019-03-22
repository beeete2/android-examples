package com.beeete2.android.examples.ui.keypad

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.beeete2.android.examples.ext.distinctUntilChanged
import com.beeete2.android.examples.ext.map
import javax.inject.Inject

class KeypadViewModel @Inject constructor() : ViewModel() {

    private val _numbers = MutableLiveData<String>("")
    val numbers = _numbers.distinctUntilChanged()

    val canDelete = numbers.map { it.isNotEmpty() }.distinctUntilChanged()
    val canInput = numbers.map { it.length < 11 }.distinctUntilChanged()
    val canCall = numbers.map { it.length == 11 }.distinctUntilChanged()


    fun enterNumber1() {
        enterNumber("1")
    }

    fun enterNumber2() {
        enterNumber("2")
    }

    fun enterNumber3() {
        enterNumber("3")
    }

    fun enterNumber4() {
        enterNumber("4")
    }

    fun enterNumber5() {
        enterNumber("5")
    }

    fun enterNumber6() {
        enterNumber("6")
    }

    fun enterNumber7() {
        enterNumber("7")
    }

    fun enterNumber8() {
        enterNumber("8")
    }

    fun enterNumber9() {
        enterNumber("9")
    }

    fun enterNumber0() {
        enterNumber("0")
    }

    fun enterDelete() {
        val input = _numbers.value
        input ?: return

        _numbers.value = input.take(input.length - 1)
    }

    fun enterNumber(input: String) {
        _numbers.value = _numbers.value + input
    }

}
