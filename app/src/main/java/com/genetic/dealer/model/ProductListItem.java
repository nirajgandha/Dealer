package com.genetic.dealer.model;

import com.google.gson.annotations.SerializedName;

public class ProductListItem {

	@SerializedName("image")
	private String image;

	@SerializedName("amount")
	private int amount;

	@SerializedName("category_name")
	private String categoryName;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("category_id")
	private int categoryId;

	@SerializedName("description")
	private String description;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("id")
	private int id;

	@SerializedName("title")
	private String title;

	@SerializedName("deleted_at")
	private String deletedAt;

	@SerializedName("optionCount")
	private int optionCount;

	public String getImage(){
		return image;
	}

	public int getAmount(){
		return amount;
	}

	public String getCategoryName(){
		return categoryName;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public int getCategoryId(){
		return categoryId;
	}

	public String getDescription(){
		return description;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public int getId(){
		return id;
	}

	public String getTitle(){
		return title;
	}

	public String getDeletedAt(){
		return deletedAt;
	}

	public int getOptionCount(){
		return optionCount;
	}

	@Override
 	public String toString(){
		return 
			"DataItem{" + 
			"image = '" + image + '\'' + 
			",amount = '" + amount + '\'' + 
			",category_name = '" + categoryName + '\'' + 
			",updated_at = '" + updatedAt + '\'' + 
			",category_id = '" + categoryId + '\'' + 
			",description = '" + description + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",id = '" + id + '\'' + 
			",title = '" + title + '\'' + 
			",deleted_at = '" + deletedAt + '\'' + 
			",optionCount = '" + optionCount + '\'' + 
			"}";
		}
}