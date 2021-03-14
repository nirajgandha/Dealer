package com.genetic.dealer.model;

import java.util.ArrayList;
import com.google.gson.annotations.SerializedName;

public class OrderDetailData {

	@SerializedName("order_amount")
	private OrderAmount orderAmount;

	@SerializedName("order_list")
	private ArrayList<OrderListItem> orderList;

	public OrderAmount getOrderAmount(){
		return orderAmount;
	}

	public ArrayList<OrderListItem> getOrderList(){
		return orderList;
	}

	@Override
 	public String toString(){
		return 
			"Data{" + 
			"order_amount = '" + orderAmount + '\'' + 
			",order_list = '" + orderList + '\'' + 
			"}";
		}
}