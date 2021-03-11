package com.genetic.dealer.interfaces

import com.genetic.dealer.model.Menus
import com.genetic.dealer.model.ProductCategoryItem

interface NavigationDrawerItemClickListener {
    fun onNavigationDrawerItemClick(productCategoryItem: ProductCategoryItem)
}