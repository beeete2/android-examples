package com.beeete2.android.examples.ui.epoxy

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.airbnb.epoxy.TypedEpoxyController
import com.beeete2.android.examples.R
import com.beeete2.android.examples.databinding.ActivityEpoxyInputBinding
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

class EpoxyInputActivity : DaggerAppCompatActivity() {

    private lateinit var binding: ActivityEpoxyInputBinding

    @Inject
    lateinit var viewModelProvider: Provider<EpoxyInputViewModel>

    private lateinit var viewModel: EpoxyInputViewModel

    private val controller = Controller()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = injectedViewModelProvider[viewModelProvider]

        binding = DataBindingUtil.setContentView(this, R.layout.activity_epoxy_input)
        binding.lifecycleOwner = this

        setSupportActionBar(binding.toolbar)
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setHomeButtonEnabled(true)
        }

        recycler_view.apply {
            adapter = controller.adapter
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    inner class Controller : TypedEpoxyController<Friend>() {

        override fun buildModels(data: Friend?) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

    }

    @Module
    abstract class EpoxyInputActivityModule {
        @ContributesAndroidInjector
        abstract fun contributeEpoxyInputActivity(): EpoxyInputActivity
    }


}
