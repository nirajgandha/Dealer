package com.genetic.dealer.model;

import java.util.ArrayList;
import com.google.gson.annotations.SerializedName;

public class OrderListResponse{

	@SerializedName("data")
	private ArrayList<OrderItem> data;

	@SerializedName("meta")
	private Meta meta;

	public ArrayList<OrderItem> getData(){
		return data;
	}

	public Meta getMeta(){
		return meta;
	}

	@Override
 	public String toString(){
		return 
			"OrderListResponse{" + 
			"data = '" + data + '\'' + 
			",meta = '" + meta + '\'' + 
			"}";
		}
}