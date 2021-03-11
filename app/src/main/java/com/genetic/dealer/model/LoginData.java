package com.genetic.dealer.model;

import com.google.gson.annotations.SerializedName;

public class LoginData {

	@SerializedName("code")
	private String code;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("phone")
	private String phone;

	@SerializedName("logged_in")
	private int loggedIn;

	@SerializedName("device_token")
	private String deviceToken;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("otp")
	private int otp;

	@SerializedName("device_type")
	private String deviceType;

	@SerializedName("id")
	private int id;

	@SerializedName("type")
	private String type;

	@SerializedName("deleted_at")
	private String deletedAt;

	public String getCode(){
		return code;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public String getPhone(){
		return phone;
	}

	public int getLoggedIn(){
		return loggedIn;
	}

	public String getDeviceToken(){
		return deviceToken;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public int getOtp(){
		return otp;
	}

	public String getDeviceType(){
		return deviceType;
	}

	public int getId(){
		return id;
	}

	public String getType(){
		return type;
	}

	public String getDeletedAt(){
		return deletedAt;
	}

	@Override
 	public String toString(){
		return 
			"Data{" + 
			"code = '" + code + '\'' + 
			",updated_at = '" + updatedAt + '\'' + 
			",phone = '" + phone + '\'' + 
			",logged_in = '" + loggedIn + '\'' + 
			",device_token = '" + deviceToken + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",otp = '" + otp + '\'' + 
			",device_type = '" + deviceType + '\'' + 
			",id = '" + id + '\'' + 
			",type = '" + type + '\'' + 
			",deleted_at = '" + deletedAt + '\'' + 
			"}";
		}
}