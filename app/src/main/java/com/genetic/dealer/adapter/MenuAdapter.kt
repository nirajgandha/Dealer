package com.genetic.dealer.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.genetic.dealer.R
import com.genetic.dealer.databinding.BottomNavigationItemBinding
import com.genetic.dealer.interfaces.ItemClickListener
import com.genetic.dealer.model.Menus
import java.util.*

class MenuAdapter(private var menusArrayList: ArrayList<Menus>, private val itemClickListener: ItemClickListener, private val context: Context) : RecyclerView.Adapter<MenuAdapter.ViewHolder?>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val appBinding = BottomNavigationItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(appBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            with(menusArrayList[position]){
                if (menuName.isNotBlank()) {
                    bottomNavigationItemBinding.txtMenuName.text = menuName
                    bottomNavigationItemBinding.layMainItem.setOnClickListener { itemClickListener.onItemClick(menuName) }
                    bottomNavigationItemBinding.imgMenu.setImageResource(activeIconPath)
                    if (isSelected) {
                        holder.bottomNavigationItemBinding.selectionView.setBackgroundColor(context.resources.getColor(R.color.bottom_navigation_selected_color, context.theme))
                        holder.bottomNavigationItemBinding.imgMenu.elevation = 6.0f
                    } else {
                        holder.bottomNavigationItemBinding.selectionView.setBackgroundColor(context.resources.getColor(android.R.color.transparent, context.theme))
                        holder.bottomNavigationItemBinding.imgMenu.elevation = 0.0f
                    }
                } else {
                    bottomNavigationItemBinding.root.visibility = View.GONE
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return menusArrayList.size
    }

    fun refreshList(menusArrayList: ArrayList<Menus>) {
        this.menusArrayList = menusArrayList
        notifyDataSetChanged()
    }

    class ViewHolder(val bottomNavigationItemBinding: BottomNavigationItemBinding) : RecyclerView.ViewHolder(bottomNavigationItemBinding.root)

}