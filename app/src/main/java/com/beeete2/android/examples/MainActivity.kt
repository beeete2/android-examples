package com.beeete2.android.examples

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.beeete2.android.examples.ui.friends.FriendSimplyActivity
import com.beeete2.android.examples.databinding.ActivityMainBinding
import com.beeete2.android.examples.databinding.MenuItemBinding
import com.beeete2.android.examples.ui.friends.FriendBarrierActivity
import com.beeete2.android.examples.ui.friends.FriendFullyActivity
import com.beeete2.android.examples.ui.friends.FriendMinHeightActivity
import com.beeete2.android.examples.ui.keypad.KeypadActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding =  DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.menus.apply {
            setHasFixedSize(true)
            addItemDecoration(DividerItemDecoration(applicationContext, DividerItemDecoration.VERTICAL))

            adapter = Adapter(listOf(
                MenuItem("Friend", FriendSimplyActivity::class.java),
                MenuItem("FriendFully", FriendFullyActivity::class.java),
                MenuItem("FriendBarrierActivity", FriendBarrierActivity::class.java),
                MenuItem("FriendMinHeightActivity", FriendMinHeightActivity::class.java),
                MenuItem("KeypadActivity", KeypadActivity::class.java)
            ))
        }
    }

    data class MenuItem(val name: String,  val activityClass: Class<*>)

    class Adapter(val items: List<MenuItem>) : RecyclerView.Adapter<Adapter.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val itemRowDataBinding = DataBindingUtil.inflate<MenuItemBinding>(LayoutInflater.from(parent.context), R.layout.menu_item, parent, false)
            return ViewHolder(itemRowDataBinding.root)
        }

        override fun getItemCount(): Int = items.size

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val itemView = holder.itemView
            val menuItemBinding: MenuItemBinding? = DataBindingUtil.bind<MenuItemBinding>(itemView)
            menuItemBinding?.let { binding ->
                val menu = items[position]
                binding.menu = menu
                binding.root.setOnClickListener {
                    val context = binding.root.context
                    context.startActivity(Intent(context, menu.activityClass))
                }
            }
        }

        class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
    }
}
