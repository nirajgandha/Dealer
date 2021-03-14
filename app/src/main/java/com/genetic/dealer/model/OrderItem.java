package com.genetic.dealer.model;

import com.google.gson.annotations.SerializedName;

public class OrderItem {

	@SerializedName("lr_upload")
	private String lrUpload;

	@SerializedName("saleman_name")
	private String salemanName;

	@SerializedName("due_date")
	private String dueDate;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("tax")
	private double tax;

	@SerializedName("deleted_at")
	private String deletedAt;

	@SerializedName("order_status")
	private String orderStatus;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("total_amount")
	private double totalAmount;

	@SerializedName("dealer_name")
	private String dealerName;

	@SerializedName("id")
	private int id;

	@SerializedName("invoice")
	private String invoice;

	@SerializedName("sales_person_id")
	private int salesPersonId;

	@SerializedName("order_id")
	private String orderId;

	@SerializedName("dealer_id")
	private int dealerId;

	public String getLrUpload(){
		return lrUpload;
	}

	public String getSalemanName(){
		return salemanName;
	}

	public String getDueDate(){
		return dueDate;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public double getTax(){
		return tax;
	}

	public String getDeletedAt(){
		return deletedAt;
	}

	public String getOrderStatus(){
		return orderStatus;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public double getTotalAmount(){
		return totalAmount;
	}

	public String getDealerName(){
		return dealerName;
	}

	public int getId(){
		return id;
	}

	public String getInvoice(){
		return invoice;
	}

	public int getSalesPersonId(){
		return salesPersonId;
	}

	public String getOrderId(){
		return orderId;
	}

	public int getDealerId(){
		return dealerId;
	}

	@Override
 	public String toString(){
		return 
			"DataItem{" + 
			"lr_upload = '" + lrUpload + '\'' + 
			",saleman_name = '" + salemanName + '\'' + 
			",due_date = '" + dueDate + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",tax = '" + tax + '\'' + 
			",deleted_at = '" + deletedAt + '\'' + 
			",order_status = '" + orderStatus + '\'' + 
			",updated_at = '" + updatedAt + '\'' + 
			",total_amount = '" + totalAmount + '\'' + 
			",dealer_name = '" + dealerName + '\'' + 
			",id = '" + id + '\'' + 
			",invoice = '" + invoice + '\'' + 
			",sales_person_id = '" + salesPersonId + '\'' + 
			",order_id = '" + orderId + '\'' + 
			",dealer_id = '" + dealerId + '\'' + 
			"}";
		}
}