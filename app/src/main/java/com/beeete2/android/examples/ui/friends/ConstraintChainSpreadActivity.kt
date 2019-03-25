package com.beeete2.android.examples.ui.friends

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.beeete2.android.examples.R
import com.beeete2.android.examples.databinding.ActivityConstraintChainSpreadBinding
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.android.support.DaggerAppCompatActivity

class ConstraintChainSpreadActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityConstraintChainSpreadBinding =  DataBindingUtil.setContentView(this, R.layout.activity_constraint_chain_spread)
    }

    @Module
    abstract class ConstraintChainSpreadActivityModule {
        @ContributesAndroidInjector
        abstract fun contributeConstraintChainSpreadActivity(): ConstraintChainSpreadActivity
    }

}
