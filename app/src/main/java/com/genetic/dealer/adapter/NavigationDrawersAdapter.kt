package com.genetic.dealer.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.genetic.dealer.databinding.NavigationDrawerRecyclerviewItemBinding
import com.genetic.dealer.interfaces.NavigationDrawerItemClickListener
import com.genetic.dealer.model.ProductCategoryItem
import java.util.*

class NavigationDrawersAdapter(
    private var productCategoryArrayList: ArrayList<ProductCategoryItem>,
    private val navigationDrawerItemClickListener: NavigationDrawerItemClickListener,
    private val context: Context
) : RecyclerView.Adapter<NavigationDrawersAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val appBinding = NavigationDrawerRecyclerviewItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(appBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(productCategoryArrayList[position]) {
                navigationDrawerRecyclerviewItemBinding.productTitle.text = name
                navigationDrawerRecyclerviewItemBinding.root.setOnClickListener {
                    navigationDrawerItemClickListener.onNavigationDrawerItemClick(this)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return productCategoryArrayList.size
    }

    fun refreshList(productCategoryArrayList: ArrayList<ProductCategoryItem>) {
        this.productCategoryArrayList = productCategoryArrayList
        notifyDataSetChanged()
    }

    class ViewHolder(val navigationDrawerRecyclerviewItemBinding: NavigationDrawerRecyclerviewItemBinding) :
        RecyclerView.ViewHolder(navigationDrawerRecyclerviewItemBinding.root)

}