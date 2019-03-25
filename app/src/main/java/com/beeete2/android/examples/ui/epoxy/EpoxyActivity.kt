package com.beeete2.android.examples.ui.epoxy

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import com.airbnb.epoxy.EpoxyController
import com.beeete2.android.examples.*
import com.beeete2.android.examples.databinding.ActivityEpoxyBinding
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.android.support.DaggerAppCompatActivity
import me.tatarka.injectedvmprovider.ktx.injectedViewModelProvider
import javax.inject.Inject
import javax.inject.Provider

class EpoxyActivity : DaggerAppCompatActivity() {

    private lateinit var binding: ActivityEpoxyBinding

    @Inject
    lateinit var viewModelProvider: Provider<EpoxyViewModel>

    private lateinit var viewModel: EpoxyViewModel

    private val controller = ListController()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = injectedViewModelProvider[viewModelProvider]

        binding = DataBindingUtil.setContentView(this, R.layout.activity_epoxy)
        binding.lifecycleOwner = this

        setSupportActionBar(binding.toolbar)
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setHomeButtonEnabled(true)
        }

        binding.recyclerView.apply {
            setHasFixedSize(true)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            adapter = controller.adapter
        }

        controller.requestModelBuild()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    inner class ListController : EpoxyController() {

        override fun buildModels() {
            itemEpoxyHeader {
                id("header_spread")
                title("spread")
            }
            itemEpoxySpread {
                id("spread_inside")
            }
            itemEpoxyHeader {
                id("space1")
            }

            itemEpoxyHeader {
                id("header_spread_inside")
                title("spread_inside")
            }
            itemEpoxySpreadInside {
                id("spread_inside")
            }
            itemEpoxyHeader {
                id("space2")
            }

            itemEpoxyHeader {
                id("header_packed")
                title("packed")
            }
            itemEpoxyPacked {
                id("packed")
            }
        }

    }

    @Module
    abstract class EpoxyActivityModule {
        @ContributesAndroidInjector
        abstract fun contributeEpoxyActivity(): EpoxyActivity
    }

}
