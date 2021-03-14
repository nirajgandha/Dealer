package com.genetic.dealer.interfaces

import com.genetic.dealer.model.PaymentItem

interface PaymentItemClickListener {
    fun onPaymentItemClick(paymentItem: PaymentItem)
}