package com.genetic.dealer.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.genetic.dealer.R
import com.genetic.dealer.databinding.PaymentScreenRecyclerItemBinding
import com.genetic.dealer.interfaces.PaymentItemClickListener
import com.genetic.dealer.model.PaymentItem
import com.genetic.dealer.utils.GlideApp
import java.util.*

class PaymentAdapter(private var paymentItemArrayList: ArrayList<PaymentItem>, private val paymentItemClickListener: PaymentItemClickListener, private val context: Context) : RecyclerView.Adapter<PaymentAdapter.ViewHolder?>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val appBinding = PaymentScreenRecyclerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(appBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            with(paymentItemArrayList[position]){
                paymentScreenRecyclerItemBinding.orderNo.text = context.resources.getString(R.string.order_number, orderId)
                paymentScreenRecyclerItemBinding.paymentAmount.text = context.resources.getString(R.string.amount_s,
                    totalAmount.toString())
                paymentScreenRecyclerItemBinding.paymentDate.text = paymentDate.split(" ")[0]
                paymentScreenRecyclerItemBinding.paymentType.text = type
                paymentScreenRecyclerItemBinding.status.text = status
                paymentScreenRecyclerItemBinding.root.setOnClickListener {
                    paymentItemClickListener.onPaymentItemClick(this)
                }
                Log.d("Niraj", "loadDataFromResponse: $image")
                if (image.isNotEmpty()) {
                    GlideApp.with(context)
                        .load(image)
                        .fitCenter()
                        .into(paymentScreenRecyclerItemBinding.roundImgLayout.roundedImageView)
                        .onLoadFailed(
                            ResourcesCompat.getDrawable(
                                context.resources,
                                R.drawable.logo,
                                context.theme
                            )
                        )
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return paymentItemArrayList.size
    }

    class ViewHolder(val paymentScreenRecyclerItemBinding: PaymentScreenRecyclerItemBinding) : RecyclerView.ViewHolder(paymentScreenRecyclerItemBinding.root)

}