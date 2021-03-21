package com.genetic.dealer.model;

import java.util.ArrayList;
import com.google.gson.annotations.SerializedName;

public class RecentOrder{

	@SerializedName("order_amount")
	private OrderAmount orderAmount;

	@SerializedName("order_list")
	private ArrayList<DashboardOrderListItem> orderList;

	public OrderAmount getOrderAmount(){
		return orderAmount;
	}

	public ArrayList<DashboardOrderListItem> getOrderList(){
		return orderList;
	}

	@Override
 	public String toString(){
		return 
			"RecentOrder{" + 
			"order_amount = '" + orderAmount + '\'' + 
			",order_list = '" + orderList + '\'' + 
			"}";
		}
}