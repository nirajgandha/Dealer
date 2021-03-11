package com.genetic.dealer.model

class Menus {
    var menuId = 0
    var activeIconPath = 0
    var menuName: String = ""
    var isSelected = false
    override fun toString(): String {
        return this.javaClass.simpleName +
                " {\"menuId\":\"" + menuId + "\"," +
                " \"activeIconPath:\"" + activeIconPath + "\"," +
                " \"menuName:\"" + menuName + "\"," +
                " \"isSelected:\"" + isSelected + "\"}"
    }
}