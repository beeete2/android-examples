package com.beeete2.android.examples.ui.friends

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import com.airbnb.epoxy.EpoxyController
import com.beeete2.android.examples.*
import com.beeete2.android.examples.databinding.ActivityConstraintEllipsizeBinding
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.android.support.DaggerAppCompatActivity

class ConstraintEllipsizeActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityConstraintEllipsizeBinding =  DataBindingUtil.setContentView(this, R.layout.activity_constraint_ellipsize)
        val controller = Controller()

        binding.recyclerView.apply {
            setHasFixedSize(true)
            addItemDecoration(DividerItemDecoration(applicationContext, DividerItemDecoration.VERTICAL))
            adapter = controller.adapter
        }

        controller.requestModelBuild()
    }

    inner class Controller: EpoxyController() {
        override fun buildModels() {
            itemConstraintlayoutEllipsize {
                id("wrap_content")
                item(Item("wrap_content"))
            }
            itemConstraintlayoutEllipsizeWidth {
                id("wrap_content and layout_constrainedWidth")
                item(Item("wrap_content and layout_constrainedWidth"))
            }
            itemConstraintlayoutEllipsizeMatchConstraint {
                id("match_constraint")
                item(Item("match_constraint"))
            }
        }
    }

    data class Item(
        val title: String
    )

    @Module
    abstract class ConstraintEllipsizeActivityActivityModule {
        @ContributesAndroidInjector
        abstract fun contributeConstraintEllipsizeActivity(): ConstraintEllipsizeActivity
    }
}
