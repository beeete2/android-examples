package com.beeete2.android.examples.ui.keypad

import com.beeete2.android.examples.ext.TestObserver
import com.beeete2.android.examples.ext.useLiveData
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature
import com.google.common.truth.Truth.assertThat

object KeypadViewModelTest : Spek({

    useLiveData()

    Feature("KeyPadViewModel LiveData") {
        val viewModel by memoized { KeypadViewModel() }
        val numbers by memoized { TestObserver<String>() }
        val canDelete by memoized { TestObserver<Boolean>() }
        val canCall by memoized { TestObserver<Boolean>() }

        Scenario("Default Values") {
            Given("ViewModel create") {
                viewModel.numbers.observeForever(numbers)
                viewModel.canDelete.observeForever(canDelete)
                viewModel.canCall.observeForever(canCall)
            }

            Then("it numbers should be empty string") {
                assertThat(numbers.get()).isEmpty()
            }

            Then("it canDelete should return false") {
                assertThat(canDelete.get()).isFalse()
            }

            Then("it canCall should return false") {
                assertThat(canCall.get()).isFalse()
            }

        }

        Scenario("Input number") {
            Given("ViewModel create") {
                viewModel.numbers.observeForever(numbers)
                viewModel.canDelete.observeForever(canDelete)
                viewModel.canCall.observeForever(canCall)
            }

            When("Input 1") {
                viewModel.enterNumber1()
            }

            Then("it numbers should be 1") {
                assertThat(numbers.get()).isEqualTo("1")
            }

            Then("it canDelete should be true") {
                assertThat(canDelete.get()).isTrue()
            }

            Then("it canCall should be false") {
                assertThat(canCall.get()).isFalse()
            }

            When("Delete number") {
                viewModel.enterDelete()
            }

            Then("it numbers should be empty string") {
                assertThat(numbers.get()).isEmpty()
            }

            Then("it canDelete should be false") {
                assertThat(canDelete.get()).isFalse()
            }
        }

        Scenario("Input number until can call") {
            Given("ViewModel create") {
                viewModel.numbers.observeForever(numbers)
                viewModel.canDelete.observeForever(canDelete)
                viewModel.canCall.observeForever(canCall)
            }

            When("Input numbers") {
                viewModel.enterNumber1()
                viewModel.enterNumber2()
                viewModel.enterNumber3()
                viewModel.enterNumber4()
                viewModel.enterNumber5()
                viewModel.enterNumber6()
                viewModel.enterNumber7()
                viewModel.enterNumber8()
                viewModel.enterNumber9()
                viewModel.enterNumber0()
                viewModel.enterNumber1()
            }

            Then("it numbers should be 12345678901") {
                assertThat(numbers.get()).isEqualTo("12345678901")
            }

            Then("it canCall should be true") {
                assertThat(canCall.get()).isTrue()
            }
        }
    }
})
