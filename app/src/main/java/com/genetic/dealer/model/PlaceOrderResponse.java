package com.genetic.dealer.model;

import com.google.gson.annotations.SerializedName;

public class PlaceOrderResponse{

	@SerializedName("data")
	private PlaceOrderData data;

	@SerializedName("meta")
	private Meta meta;

	public PlaceOrderData getData(){
		return data;
	}

	public Meta getMeta(){
		return meta;
	}

	@Override
 	public String toString(){
		return 
			"PlaceOrderResponse{" + 
			"data = '" + data + '\'' + 
			",meta = '" + meta + '\'' + 
			"}";
		}
}