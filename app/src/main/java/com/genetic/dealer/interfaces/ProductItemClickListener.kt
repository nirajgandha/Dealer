package com.genetic.dealer.interfaces

import com.genetic.dealer.model.ProductListItem

interface ProductItemClickListener {
    fun onProductItemClick(productListItem: ProductListItem)
}