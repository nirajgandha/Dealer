package com.genetic.dealer.interfaces

import com.genetic.dealer.model.ProductOption

interface ProductOptionListener {
    fun onProductOptionClick(productOption: ProductOption)
}