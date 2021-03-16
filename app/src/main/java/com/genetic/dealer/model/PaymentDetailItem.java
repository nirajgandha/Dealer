package com.genetic.dealer.model;

import com.google.gson.annotations.SerializedName;

public class PaymentDetailItem{

	@SerializedName("image")
	private String image;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("total_amount")
	private int totalAmount;

	@SerializedName("sale_man_id")
	private int saleManId;

	@SerializedName("description")
	private String description;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("id")
	private int id;

	@SerializedName("type")
	private String type;

	@SerializedName("order_id")
	private int orderId;

	@SerializedName("deleted_at")
	private String deletedAt;

	@SerializedName("dealer_id")
	private int dealerId;

	@SerializedName("status")
	private String status;

	public String getImage(){
		return image;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public int getTotalAmount(){
		return totalAmount;
	}

	public int getSaleManId(){
		return saleManId;
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

	public String getType(){
		return type;
	}

	public int getOrderId(){
		return orderId;
	}

	public String getDeletedAt(){
		return deletedAt;
	}

	public int getDealerId(){
		return dealerId;
	}

	public String getStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"PaymentDetailItem{" + 
			"image = '" + image + '\'' + 
			",updated_at = '" + updatedAt + '\'' + 
			",total_amount = '" + totalAmount + '\'' + 
			",sale_man_id = '" + saleManId + '\'' + 
			",description = '" + description + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",id = '" + id + '\'' + 
			",type = '" + type + '\'' + 
			",order_id = '" + orderId + '\'' + 
			",deleted_at = '" + deletedAt + '\'' + 
			",dealer_id = '" + dealerId + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}