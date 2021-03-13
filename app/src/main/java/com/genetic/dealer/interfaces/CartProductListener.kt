package com.genetic.dealer.interfaces

import com.genetic.dealer.model.CustomProductOptionModel

interface CartProductListener {
    fun cartProductUpdates(key: String, value: CustomProductOptionModel?)
}