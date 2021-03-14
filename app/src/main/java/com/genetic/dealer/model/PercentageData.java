package com.genetic.dealer.model;

import com.google.gson.annotations.SerializedName;

public class PercentageData {

	@SerializedName("value")
	private String value;

	public String getValue(){
		return value;
	}

	@Override
 	public String toString(){
		return 
			"Data{" + 
			"value = '" + value + '\'' + 
			"}";
		}
}