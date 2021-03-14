package com.genetic.dealer.model;

import com.google.gson.annotations.SerializedName;

public class UpdateProfileResponse{

	@SerializedName("data")
	private UpdateProfileData data;

	@SerializedName("meta")
	private Meta meta;

	public UpdateProfileData getData(){
		return data;
	}

	public Meta getMeta(){
		return meta;
	}

	@Override
 	public String toString(){
		return 
			"UpdateProfileResponse{" + 
			"data = '" + data + '\'' + 
			",meta = '" + meta + '\'' + 
			"}";
		}
}