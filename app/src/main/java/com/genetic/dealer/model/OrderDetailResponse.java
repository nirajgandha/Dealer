package com.genetic.dealer.model;

import com.google.gson.annotations.SerializedName;

public class OrderDetailResponse{

	@SerializedName("data")
	private OrderDetailData orderDetailData;

	@SerializedName("meta")
	private Meta meta;

	public OrderDetailData getOrderDetailData(){
		return orderDetailData;
	}

	public Meta getMeta(){
		return meta;
	}

	@Override
 	public String toString(){
		return 
			"OrderDetailResponse{" + 
			"data = '" + orderDetailData + '\'' +
			",meta = '" + meta + '\'' + 
			"}";
		}
}