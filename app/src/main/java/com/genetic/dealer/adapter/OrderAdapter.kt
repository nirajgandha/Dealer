package com.genetic.dealer.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.genetic.dealer.R
import com.genetic.dealer.databinding.OrderScreenRecyclerItemBinding
import com.genetic.dealer.databinding.ProductScreenRecyclerItemBinding
import com.genetic.dealer.interfaces.OrderItemClickListener
import com.genetic.dealer.interfaces.ProductItemClickListener
import com.genetic.dealer.model.OrderItem
import com.genetic.dealer.model.ProductListItem
import java.util.*

class OrderAdapter(private var orderItemList: ArrayList<OrderItem>, private val orderItemClickListener: OrderItemClickListener, private val context: Context) : RecyclerView.Adapter<OrderAdapter.ViewHolder?>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val appBinding = OrderScreenRecyclerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(appBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            with(orderItemList[position]){
                orderScreenRecyclerItemBinding.orderTitle.text = context.resources.getString(R.string.order_number, orderId)
                orderScreenRecyclerItemBinding.salesManName.text = salemanName
                orderScreenRecyclerItemBinding.date.text = dueDate
                orderScreenRecyclerItemBinding.amount.text = context.resources.getString(R.string.amount_s,
                    totalAmount.toString())
                orderScreenRecyclerItemBinding.status.text = orderStatus
                orderScreenRecyclerItemBinding.root.setOnClickListener {
                    orderItemClickListener.onOrderItemClick(this)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return orderItemList.size
    }

    class ViewHolder(val orderScreenRecyclerItemBinding: OrderScreenRecyclerItemBinding) : RecyclerView.ViewHolder(orderScreenRecyclerItemBinding.root)

}