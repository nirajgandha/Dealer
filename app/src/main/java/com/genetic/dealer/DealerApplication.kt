package com.genetic.dealer

import android.app.Application
import com.genetic.dealer.model.CustomProductOptionModel

class DealerApplication: Application() {

    private var productCartList: HashMap<String, CustomProductOptionModel>? = null
    private var keyArrayList: ArrayList<String>? = null
    private var mProductCategory: ArrayList<String>? = null

    override fun onCreate() {
        super.onCreate()
        productCartList = HashMap()
        keyArrayList = ArrayList()
    }

    fun getProductCartList(): HashMap<String, CustomProductOptionModel> {
        if (productCartList == null) {
            productCartList = HashMap()
        }
        return productCartList!!
    }

    fun getKeyArrayList(): ArrayList<String> {
        if (keyArrayList == null) {
            keyArrayList = ArrayList()
        }
        return keyArrayList!!
    }

    fun getProductCategoryDetail(): ArrayList<String>? {
        return mProductCategory
    }
}