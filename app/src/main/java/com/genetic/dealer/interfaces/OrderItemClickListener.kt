package com.genetic.dealer.interfaces

import com.genetic.dealer.model.OrderItem

interface OrderItemClickListener {
    fun onOrderItemClick(orderItem: OrderItem)
}