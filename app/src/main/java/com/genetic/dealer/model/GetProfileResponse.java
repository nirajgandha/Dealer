package com.genetic.dealer.model;

import java.util.ArrayList;
import com.google.gson.annotations.SerializedName;

public class GetProfileResponse{

	@SerializedName("data")
	private ArrayList<ProfileItem> data;

	@SerializedName("meta")
	private Meta meta;

	public ArrayList<ProfileItem> getData(){
		return data;
	}

	public Meta getMeta(){
		return meta;
	}

	@Override
 	public String toString(){
		return 
			"GetProfileResponse{" + 
			"data = '" + data + '\'' + 
			",meta = '" + meta + '\'' + 
			"}";
		}
}