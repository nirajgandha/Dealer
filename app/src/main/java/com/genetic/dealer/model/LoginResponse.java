package com.genetic.dealer.model;

import com.google.gson.annotations.SerializedName;

public class LoginResponse{

	@SerializedName("data")
	private LoginData loginData;

	@SerializedName("meta")
	private Meta meta;

	public LoginData getLoginData(){
		return loginData;
	}

	public Meta getMeta(){
		return meta;
	}

	@Override
 	public String toString(){
		return 
			"LoginResponse{" + 
			"data = '" + loginData + '\'' +
			",meta = '" + meta + '\'' + 
			"}";
		}
}