package com.genetic.dealer.model;

import com.google.gson.annotations.SerializedName;

public class ProductCategoryItem {

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("parent_id")
	private String parentId;

	@SerializedName("name")
	private String name;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("id")
	private int id;

	@SerializedName("slug")
	private String slug;

	@SerializedName("order")
	private int order;

	public String getUpdatedAt(){
		return updatedAt;
	}

	public String getParentId(){
		return parentId;
	}

	public String getName(){
		return name;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public int getId(){
		return id;
	}

	public String getSlug(){
		return slug;
	}

	public int getOrder(){
		return order;
	}

	@Override
 	public String toString(){
		return 
			"DataItem{" + 
			"updated_at = '" + updatedAt + '\'' + 
			",parent_id = '" + parentId + '\'' + 
			",name = '" + name + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",id = '" + id + '\'' + 
			",slug = '" + slug + '\'' + 
			",order = '" + order + '\'' + 
			"}";
		}
}