package com.genetic.dealer.model;

import java.util.ArrayList;
import com.google.gson.annotations.SerializedName;

public class ProductCategoryResponse{

	@SerializedName("data")
	private ArrayList<ProductCategoryItem> data;

	@SerializedName("meta")
	private Meta meta;

	public ArrayList<ProductCategoryItem> getData(){
		return data;
	}

	public Meta getMeta(){
		return meta;
	}

	@Override
 	public String toString(){
		return 
			"ProductCategoryResponse{" + 
			"data = '" + data + '\'' + 
			",meta = '" + meta + '\'' + 
			"}";
		}
}