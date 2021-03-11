package com.genetic.dealer.model;

import java.util.ArrayList;
import com.google.gson.annotations.SerializedName;

public class ProductOptionListResponse{

	@SerializedName("data")
	private ArrayList<ProductOption> data;

	@SerializedName("meta")
	private Meta meta;

	public ArrayList<ProductOption> getData(){
		return data;
	}

	public Meta getMeta(){
		return meta;
	}

	@Override
 	public String toString(){
		return 
			"ProductOptionListResponse{" + 
			"data = '" + data + '\'' + 
			",meta = '" + meta + '\'' + 
			"}";
		}
}