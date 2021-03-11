package com.genetic.dealer.model;

import java.util.ArrayList;
import com.google.gson.annotations.SerializedName;

public class ProductListResponse{

	@SerializedName("data")
	private ArrayList<ProductListItem> data;

	@SerializedName("meta")
	private Meta meta;

	public ArrayList<ProductListItem> getData(){
		return data;
	}

	public Meta getMeta(){
		return meta;
	}

	@Override
 	public String toString(){
		return 
			"ProductListResponse{" + 
			"data = '" + data + '\'' + 
			",meta = '" + meta + '\'' + 
			"}";
		}
}