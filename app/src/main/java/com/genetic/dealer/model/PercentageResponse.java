package com.genetic.dealer.model;

import com.google.gson.annotations.SerializedName;

public class PercentageResponse{

	@SerializedName("data")
	private PercentageData data;

	@SerializedName("meta")
	private Meta meta;

	public PercentageData getData(){
		return data;
	}

	public Meta getMeta(){
		return meta;
	}

	@Override
 	public String toString(){
		return 
			"PercentageResponse{" + 
			"data = '" + data + '\'' + 
			",meta = '" + meta + '\'' + 
			"}";
		}
}