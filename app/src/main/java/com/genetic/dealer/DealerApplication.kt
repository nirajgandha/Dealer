package com.genetic.dealer

import android.app.Application
import com.genetic.dealer.model.CustomProductOptionModel

class DealerApplication: Application() {

    private var productCartList: HashMap<String, CustomProductOptionModel>? = null
    override fun onCreate() {
        super.onCreate()
        productCartList = HashMap()
    }

    fun getProductCartList(): HashMap<String, CustomProductOptionModel> {
        if (productCartList == null) {
            productCartList = HashMap()
        }
        return productCartList!!
    }
}