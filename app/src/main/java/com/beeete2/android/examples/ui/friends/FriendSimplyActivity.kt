package com.beeete2.android.examples.ui.friends

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.beeete2.android.examples.R
import com.beeete2.android.examples.databinding.ActivityFriendsBinding
import com.beeete2.android.examples.databinding.RowFriendSimplyBinding
import com.beeete2.android.examples.ext.observeNonNull
import com.beeete2.android.examples.model.entity.Friend
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.android.support.DaggerAppCompatActivity
import me.tatarka.injectedvmprovider.ktx.injectedViewModelProvider
import javax.inject.Inject
import javax.inject.Provider

class FriendSimplyActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelProvider: Provider<FriendSimplyViewModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val adapter = Adapter()
        val binding: ActivityFriendsBinding =  DataBindingUtil.setContentView(this, R.layout.activity_friends)
        binding.recyclerView.apply {
            setHasFixedSize(true)
            addItemDecoration(DividerItemDecoration(applicationContext, DividerItemDecoration.VERTICAL))
            this.adapter = adapter
        }

        val viewModel = injectedViewModelProvider[viewModelProvider]
        viewModel.friends.observeNonNull(this) {
            adapter.updateItems(it)
            adapter.notifyDataSetChanged()
        }
    }

    class Adapter : RecyclerView.Adapter<Adapter.ViewHolder>() {

        private var items: List<Friend> = emptyList()

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val rowDataBinding = DataBindingUtil.inflate<RowFriendSimplyBinding>(LayoutInflater.from(parent.context), R.layout.row_friend_simply, parent, false)
            return ViewHolder(rowDataBinding.root)
        }

        override fun getItemCount(): Int = items.size

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val itemView = holder.itemView
            val rowDataBinding: RowFriendSimplyBinding? = DataBindingUtil.bind(itemView)
            rowDataBinding?.let { binding ->
                binding.friend = items[position]
            }
        }

        fun updateItems(items: List<Friend>) {
            this.items = items
        }

        class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
    }

    @Module
    abstract class FriendActivityModule {
        @ContributesAndroidInjector
        abstract fun contributeFriendActivity(): FriendSimplyActivity
    }
}
