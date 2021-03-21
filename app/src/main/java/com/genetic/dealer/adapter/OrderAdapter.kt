package com.genetic.dealer.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.genetic.dealer.R
import com.genetic.dealer.databinding.OrderScreenRecyclerItemBinding
import com.genetic.dealer.interfaces.OrderItemClickListener
import com.genetic.dealer.model.OrderItem
import java.util.*
import java.util.concurrent.TimeUnit

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
                if (lrUpload.isEmpty()) {
                    orderScreenRecyclerItemBinding.lrUploaded.text = context.resources.getString(R.string.lr_uploaded_s, "No")
                } else {
                    orderScreenRecyclerItemBinding.lrUploaded.text = context.resources.getString(R.string.lr_uploaded_s, "Yes")
                }
                if (dueDate.isNotEmpty() && dueDate.length == "yyyy-mm-dd hh:mm:ss".length) {
                    val date = dueDate.split("-")
                    orderScreenRecyclerItemBinding.date.text = "${date[2]}-${date[1]}-${date[0]}"
                    val dateCal = Calendar.getInstance()
                    dateCal.set(Calendar.YEAR, date[0].toInt())
                    dateCal.set(Calendar.MONTH, date[1].toInt())
                    dateCal.set(Calendar.DAY_OF_MONTH, date[2].toInt())
                    val timeDiff = Calendar.getInstance().timeInMillis - dateCal.timeInMillis
                    val days = TimeUnit.MILLISECONDS.toDays(timeDiff)
                    val drawables = ResourcesCompat.getDrawable(context.resources, R.drawable.ic_head_background, context.theme)
                    when {
                        days >= 120 -> {
                            drawables?.setTint(Color.parseColor("#ff00ff"))
                            drawables?.setTint(context.getColor(android.R.color.holo_red_dark))
                        }
                        days >= 90 -> {
                            drawables?.setTint(context.getColor(android.R.color.holo_blue_dark))
                        }
                        days >= 60 -> {
                            drawables?.setTint(context.getColor(R.color.green))
                        }
                        else -> {
                            drawables?.setTint(context.getColor(R.color.bottom_navigation_bar_color))
                        }
                    }
                    orderScreenRecyclerItemBinding.orderTitle.background = drawables
                }
                orderScreenRecyclerItemBinding.amount.text = context.resources.getString(R.string.amount_s,
                    totalAmount.toString())
                orderScreenRecyclerItemBinding.status.text = orderStatus
                orderScreenRecyclerItemBinding.more.setOnClickListener {
                    val popup = PopupMenu(context, orderScreenRecyclerItemBinding.more)
                    popup.inflate(R.menu.order_navigation)
                    popup.setOnMenuItemClickListener {
                        if (it.itemId == R.id.view_detail) {
                            orderItemClickListener.onOrderItemClick(this)
                            popup.dismiss()
                        }
                        true
                    }
                    popup.show()

                }
            }
        }
    }

    override fun getItemCount(): Int {
        return orderItemList.size
    }

    class ViewHolder(val orderScreenRecyclerItemBinding: OrderScreenRecyclerItemBinding) : RecyclerView.ViewHolder(orderScreenRecyclerItemBinding.root)

}