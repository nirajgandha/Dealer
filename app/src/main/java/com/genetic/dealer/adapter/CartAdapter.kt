package com.genetic.dealer.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.genetic.dealer.DealerApplication
import com.genetic.dealer.R
import com.genetic.dealer.databinding.CartItemRecyclerLayoutBinding
import com.genetic.dealer.interfaces.CartProductListener
import com.genetic.dealer.utils.GlideApp

class CartAdapter(private val cartProductListener: CartProductListener, private val context: Context) : RecyclerView.Adapter<CartAdapter.ViewHolder?>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val appBinding = CartItemRecyclerLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(appBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            val key = (context.applicationContext as DealerApplication).getKeyArrayList()[position]
            val customProductOptionModel = (context.applicationContext as DealerApplication).getProductCartList().getValue(key)
            cartItemRecyclerLayoutBinding.productTitle.text = customProductOptionModel.productOption.productName
            cartItemRecyclerLayoutBinding.productDescription.text = customProductOptionModel.productOption.categoryName
            cartItemRecyclerLayoutBinding.productAmount.text = customProductOptionModel.productOption.optionAmount.toString()
            cartItemRecyclerLayoutBinding.qty.text = customProductOptionModel.quantity.toString()
            cartItemRecyclerLayoutBinding.btnMinus.setOnClickListener {
                if (customProductOptionModel.quantity == 1) {
                    removeItems(key)
                    cartProductListener.cartProductUpdates("", null)
                } else {
                    customProductOptionModel.quantity = customProductOptionModel.quantity - 1
                    (context.applicationContext as DealerApplication).getProductCartList()[key] =
                        customProductOptionModel
                    notifyDataSetChanged()
                    cartProductListener.cartProductUpdates(key, customProductOptionModel)
                }
            }
            cartItemRecyclerLayoutBinding.btnPlus.setOnClickListener {
                customProductOptionModel.quantity = customProductOptionModel.quantity + 1
                (context.applicationContext as DealerApplication).getProductCartList()[key] =
                        customProductOptionModel
                cartProductListener.cartProductUpdates(key, customProductOptionModel)
                notifyDataSetChanged()
            }

            cartItemRecyclerLayoutBinding.delete.setOnClickListener {
                removeItems(key)
                cartProductListener.cartProductUpdates(key, customProductOptionModel)
            }
            GlideApp.with(context).load(customProductOptionModel.imageUrl)
                .into(cartItemRecyclerLayoutBinding.roundImgLayout.roundedImageView)
                .onLoadFailed(ResourcesCompat.getDrawable(context.resources, R.drawable.logo, context.theme))

        }
    }

    private fun removeItems(key: String) {
        (context.applicationContext as DealerApplication).getProductCartList().remove(key)
        (context.applicationContext as DealerApplication).getKeyArrayList().remove(key)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return (context.applicationContext as DealerApplication).getKeyArrayList().size
    }

    class ViewHolder(val cartItemRecyclerLayoutBinding: CartItemRecyclerLayoutBinding) : RecyclerView.ViewHolder(cartItemRecyclerLayoutBinding.root)

}