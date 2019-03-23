package com.beeete2.android.examples.truth

import com.google.common.truth.Truth
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature
import java.io.FileNotFoundException
import java.lang.Exception

object TruthLearn : Spek({

    Feature("string assertion") {
        lateinit var content: String

        Scenario("assertion") {
            Given("setup") {
                content = "Hello World."
            }

            Then("assertion") {
                Truth.assertThat(content).apply {
                    startsWith("Hello")
                    endsWith("World.")
                    contains(" ")
                }
            }
        }
    }

    Feature("object assertion") {
        val task by memoized {
            Task(
                id = 1,
                title = "title",
                description = "description"
            )
        }

        Scenario("simple assert") {
            Then("assertion") {
                Truth.assertThat(task.id).isEqualTo(1)
                Truth.assertThat(task.title).isEqualTo("title")
                Truth.assertThat(task.description).isEqualTo("description")
            }
        }

        Scenario("assert with kotlin apply") {
            Then("assertion") {
                task.apply {
                    Truth.assertThat(id).isEqualTo(1)
                    Truth.assertThat(title).isEqualTo("title")
                    Truth.assertThat(description).isEqualTo("description")
                }
            }
        }

        Scenario("fluent assert with kotlin apply") {
            Then("assertion") {
                Truth.assertThat(task.title).apply {
                    isNotEmpty()
                    startsWith("t")
                    contains("itl")
                    endsWith("e")
                    doesNotContain("description")
                }
            }
        }
    }

    Feature("list assertion") {
        val tasks by memoized {
            listOf(
                Task(1, "task1"),
                Task(2, "task2"),
                Task(3, "task3"),
                Task(4, "task4")
            )
        }

        Scenario("simple assert") {
            Then("assertion") {
                val titles = tasks.map { it.title }
                Truth.assertThat(titles).hasSize(4)
                Truth.assertThat(titles)
                    .containsExactly("task1", "task2", "task3", "task4")
                    .inOrder()
            }
        }

        Scenario("assert with transforming") {
            Then("assertion") {
                val transforming = TruthTransforming.transforming(Task::title.toFunction(), "has title")
                Truth.assertThat(tasks).apply {
                    hasSize(4)
                    comparingElementsUsing(transforming)
                        .containsExactly("task1", "task2", "task3", "task4")
                        .inOrder()
                }
            }
        }
    }

    Feature("exception assertion") {
        val exception by memoized {
            Exception("exception", FileNotFoundException("cause"))
        }

        Scenario("simple assert") {
            Then("assertion") {
                Truth.assertThat(exception).isInstanceOf(Exception::class.java)
                Truth.assertThat(exception).hasMessageThat().isEqualTo("exception")
                Truth.assertThat(exception).hasCauseThat().isInstanceOf(FileNotFoundException::class.java)
                Truth.assertThat(exception).hasCauseThat().hasMessageThat().isEqualTo("cause")
            }
        }

        Scenario("fluent assert with kotlin apply") {
            Then("assertion") {
                Truth.assertThat(exception).apply {
                    isInstanceOf(Exception::class.java)
                    hasMessageThat().isEqualTo("exception")
                    hasCauseThat().isInstanceOf(FileNotFoundException::class.java)
                    hasCauseThat().hasMessageThat().isEqualTo("cause")
                }
            }
        }
    }

})
