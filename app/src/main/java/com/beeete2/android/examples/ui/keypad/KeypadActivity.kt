package com.beeete2.android.examples.ui.keypad

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.beeete2.android.examples.R
import com.beeete2.android.examples.databinding.ActivityKeypadBinding
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.android.support.DaggerAppCompatActivity
import me.tatarka.injectedvmprovider.ktx.injectedViewModelProvider
import javax.inject.Inject
import javax.inject.Provider

class KeypadActivity : DaggerAppCompatActivity() {

    private lateinit var binding: ActivityKeypadBinding

    @Inject
    lateinit var viewModelProvider: Provider<KeypadViewModel>

    private lateinit var viewModel: KeypadViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = injectedViewModelProvider[viewModelProvider]

        binding = DataBindingUtil.setContentView(this, R.layout.activity_keypad)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

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
    abstract class KeypadActivityModule {
        @ContributesAndroidInjector
        abstract fun contributeKeypadActivity(): KeypadActivity
    }
}
