package com.genetic.dealer.model;

import com.google.gson.annotations.SerializedName;

public class ProductOption {

	@SerializedName("option_title")
	private String optionTitle;

	@SerializedName("option_amount")
	private int optionAmount;

	@SerializedName("category_name")
	private String categoryName;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("product_id")
	private int productId;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("id")
	private int id;

	@SerializedName("deleted_at")
	private String deletedAt;

	@SerializedName("product_name")
	private String productName;

	public String getOptionTitle(){
		return optionTitle;
	}

	public int getOptionAmount(){
		return optionAmount;
	}

	public String getCategoryName(){
		return categoryName;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public int getProductId(){
		return productId;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public int getId(){
		return id;
	}

	public String getDeletedAt(){
		return deletedAt;
	}

	public String getProductName(){
		return productName;
	}

	@Override
 	public String toString(){
		return 
			"DataItem{" + 
			"option_title = '" + optionTitle + '\'' + 
			",option_amount = '" + optionAmount + '\'' + 
			",category_name = '" + categoryName + '\'' + 
			",updated_at = '" + updatedAt + '\'' + 
			",product_id = '" + productId + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",id = '" + id + '\'' + 
			",deleted_at = '" + deletedAt + '\'' + 
			",product_name = '" + productName + '\'' + 
			"}";
		}
}