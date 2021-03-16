package com.genetic.dealer.model;

import com.google.gson.annotations.SerializedName;

public class DashboardResponse{

	@SerializedName("data")
	private DashboardData data;

	@SerializedName("meta")
	private Meta meta;

	public DashboardData getData(){
		return data;
	}

	public Meta getMeta(){
		return meta;
	}

	@Override
 	public String toString(){
		return 
			"DashboardResponse{" + 
			"data = '" + data + '\'' + 
			",meta = '" + meta + '\'' + 
			"}";
		}
}