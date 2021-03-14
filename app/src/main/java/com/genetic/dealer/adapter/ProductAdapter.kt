package com.genetic.dealer.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.genetic.dealer.R
import com.genetic.dealer.databinding.ProductScreenRecyclerItemBinding
import com.genetic.dealer.interfaces.ProductItemClickListener
import com.genetic.dealer.model.ProductListItem
import com.genetic.dealer.utils.GlideApp
import java.util.*

class ProductAdapter(private var productItemArrayList: ArrayList<ProductListItem>, private val productItemClickListener: ProductItemClickListener, private val context: Context) : RecyclerView.Adapter<ProductAdapter.ViewHolder?>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val appBinding = ProductScreenRecyclerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(appBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            with(productItemArrayList[position]){
                productScreenRecyclerItemBinding.productTitle.text = title
                productScreenRecyclerItemBinding.productDescription.text = description
                productScreenRecyclerItemBinding.productAmount.text = context.resources.getString(R.string.amount_s,
                    amount.toString())
                productScreenRecyclerItemBinding.root.setOnClickListener {
                    productItemClickListener.onProductItemClick(this)
                }
                GlideApp.with(context).load(image)
                    .into(productScreenRecyclerItemBinding.roundImgLayout.roundedImageView)
                    .onLoadFailed(ResourcesCompat.getDrawable(context.resources, R.drawable.logo, context.theme))
            }
        }
    }

    override fun getItemCount(): Int {
        return productItemArrayList.size
    }

    class ViewHolder(val productScreenRecyclerItemBinding: ProductScreenRecyclerItemBinding) : RecyclerView.ViewHolder(productScreenRecyclerItemBinding.root)

}