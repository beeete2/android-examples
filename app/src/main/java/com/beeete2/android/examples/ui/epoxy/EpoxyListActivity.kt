package com.beeete2.android.examples.ui.epoxy

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.airbnb.epoxy.TypedEpoxyController
import com.beeete2.android.examples.*
import com.beeete2.android.examples.databinding.ActivityEpoxyListBinding
import com.beeete2.android.examples.ext.observeNonNull
import com.beeete2.android.examples.model.entity.Friend
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_epoxy_list.*
import me.tatarka.injectedvmprovider.ktx.injectedViewModelProvider
import javax.inject.Inject
import javax.inject.Provider

class EpoxyListActivity : DaggerAppCompatActivity() {


    private lateinit var binding: ActivityEpoxyListBinding

    @Inject
    lateinit var viewModelProvider: Provider<EpoxyListViewModel>

    private lateinit var viewModel: EpoxyListViewModel

    private val controller = ListController()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = injectedViewModelProvider[viewModelProvider]

        binding = DataBindingUtil.setContentView(this, R.layout.activity_epoxy_list)
        binding.lifecycleOwner = this

        setSupportActionBar(binding.toolbar)
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setHomeButtonEnabled(true)
        }

        binding.recyclerView.apply {
            adapter = controller.adapter
        }

        load_button.setOnClickListener {
            viewModel.fetch()
        }

        empty_button.setOnClickListener {
            viewModel.fetchEmpty()
        }

        error_button.setOnClickListener {
            viewModel.fetchError()
        }

        viewModel.friend.observeNonNull(this) {
            controller.setData(it)
        }
    }


    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    inner class ListController : TypedEpoxyController<FetchResult<List<Friend>>>() {
        override fun buildModels(data: FetchResult<List<Friend>>?) {
            data ?: return

            when (data) {
                is FetchResult.Unloaded -> itemEpoxyUnloaded { id("unloaded") }
                is FetchResult.Loading -> itemEpoxyLoading { id("loading") }
                is FetchResult.Success -> {
                    if (data.result.isEmpty()) {
                        itemEpoxyEmpty {
                            id("empty")
                        }
                    } else {
                        data.result.forEach {
                            itemEpoxyFriend {
                                id(it.id)
                                friend(it)
                            }
                        }
                    }
                }
                is FetchResult.Failure -> itemEpoxyError { id("error") }
            }
        }
    }

    @Module
    abstract class EpoxyListActivityModule {
        @ContributesAndroidInjector
        abstract fun contributeEpoxyListActivity(): EpoxyListActivity
    }

}
