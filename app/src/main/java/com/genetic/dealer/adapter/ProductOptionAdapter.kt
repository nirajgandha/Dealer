package com.genetic.dealer.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.RadioButton
import androidx.recyclerview.widget.RecyclerView
import com.genetic.dealer.R
import com.genetic.dealer.databinding.RadioTextRecyclerItemBinding
import com.genetic.dealer.interfaces.ProductItemClickListener
import com.genetic.dealer.interfaces.ProductOptionListener
import com.genetic.dealer.model.ProductListItem
import com.genetic.dealer.model.ProductOption
import java.util.*

class ProductOptionAdapter(private var productOptionList: ArrayList<ProductOption>, private val productOptionListener: ProductOptionListener, private val context: Context) : RecyclerView.Adapter<ProductOptionAdapter.ViewHolder?>() {
    private var checkedRadioButton: CompoundButton ?= null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val appBinding = RadioTextRecyclerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(appBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            with(productOptionList[position]){
                radioTextRecyclerItemBinding.radioButtons.text = optionTitle
                radioTextRecyclerItemBinding.radioButtons.setOnCheckedChangeListener { buttonView, isChecked ->
                    checkedRadioButton?.apply { setChecked(!isChecked) }
                    checkedRadioButton = buttonView.apply { setChecked(isChecked) }
                    productOptionListener.onProductOptionClick(this)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return productOptionList.size
    }

    class ViewHolder(val radioTextRecyclerItemBinding: RadioTextRecyclerItemBinding) : RecyclerView.ViewHolder(radioTextRecyclerItemBinding.root)

}