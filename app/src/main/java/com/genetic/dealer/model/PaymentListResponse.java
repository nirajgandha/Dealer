package com.genetic.dealer.model;

import java.util.ArrayList;
import com.google.gson.annotations.SerializedName;

public class PaymentListResponse{

	@SerializedName("data")
	private ArrayList<PaymentItem> data;

	@SerializedName("meta")
	private Meta meta;

	public ArrayList<PaymentItem> getData(){
		return data;
	}

	public Meta getMeta(){
		return meta;
	}

	@Override
 	public String toString(){
		return 
			"PaymentListResponse{" + 
			"data = '" + data + '\'' + 
			",meta = '" + meta + '\'' + 
			"}";
		}
}