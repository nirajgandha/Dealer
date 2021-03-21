package com.genetic.dealer.model;

import com.google.gson.annotations.SerializedName;

public class DashboardPaymentItem{

	@SerializedName("image")
	private String image;

	@SerializedName("due_date")
	private String dueDate;

	@SerializedName("description")
	private String description;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("type")
	private String type;

	@SerializedName("deleted_at")
	private String deletedAt;

	@SerializedName("order_ref_id")
	private String orderRefId;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("total_amount")
	private int totalAmount;

	@SerializedName("sale_man_id")
	private int saleManId;

	@SerializedName("id")
	private int id;

	@SerializedName("order_id")
	private int orderId;

	@SerializedName("dealer_id")
	private int dealerId;

	@SerializedName("status")
	private String status;

	public String getImage(){
		return image;
	}

	public String getDueDate(){
		return dueDate;
	}

	public String getDescription(){
		return description;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public String getType(){
		return type;
	}

	public String getDeletedAt(){
		return deletedAt;
	}

	public String getOrderRefId(){
		return orderRefId;
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

	public int getId(){
		return id;
	}

	public int getOrderId(){
		return orderId;
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
			"DashboardPaymentItem{" + 
			"image = '" + image + '\'' + 
			",due_date = '" + dueDate + '\'' + 
			",description = '" + description + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",type = '" + type + '\'' + 
			",deleted_at = '" + deletedAt + '\'' + 
			",order_ref_id = '" + orderRefId + '\'' + 
			",updated_at = '" + updatedAt + '\'' + 
			",total_amount = '" + totalAmount + '\'' + 
			",sale_man_id = '" + saleManId + '\'' + 
			",id = '" + id + '\'' + 
			",order_id = '" + orderId + '\'' + 
			",dealer_id = '" + dealerId + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}