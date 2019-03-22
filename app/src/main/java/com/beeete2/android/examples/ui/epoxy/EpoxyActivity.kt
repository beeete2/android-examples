package com.beeete2.android.examples.ui.epoxy

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.beeete2.android.examples.R
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
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    @Module
    abstract class EpoxyActivityModule {
        @ContributesAndroidInjector
        abstract fun contributeEpoxyActivity(): EpoxyActivity
    }

}
