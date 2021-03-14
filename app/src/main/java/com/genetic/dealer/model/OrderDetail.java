package com.genetic.dealer.model;

import com.google.gson.annotations.SerializedName;

public class OrderDetail {

	@SerializedName("lr_upload")
	private String lrUpload;

	@SerializedName("amount")
	private int amount;

	@SerializedName("product_option_title")
	private String productOptionTitle;

	@SerializedName("salesman_code")
	private String salesmanCode;

	@SerializedName("saleman_name")
	private String salemanName;

	@SerializedName("due_date")
	private String dueDate;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("tax")
	private int tax;

	@SerializedName("deleted_at")
	private String deletedAt;

	@SerializedName("order_status")
	private String orderStatus;

	@SerializedName("order_ref_id")
	private int orderRefId;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("total_amount")
	private int totalAmount;

	@SerializedName("product_id")
	private int productId;

	@SerializedName("qty")
	private int qty;

	@SerializedName("dealer_name")
	private String dealerName;

	@SerializedName("salesman_email")
	private String salesmanEmail;

	@SerializedName("id")
	private int id;

	@SerializedName("sales_person_id")
	private int salesPersonId;

	@SerializedName("order_id")
	private String orderId;

	@SerializedName("salesman_mobileno")
	private int salesmanMobileno;

	@SerializedName("dealer_id")
	private int dealerId;

	public String getLrUpload(){
		return lrUpload;
	}

	public int getAmount(){
		return amount;
	}

	public String getProductOptionTitle(){
		return productOptionTitle;
	}

	public String getSalesmanCode(){
		return salesmanCode;
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

	public int getTax(){
		return tax;
	}

	public String getDeletedAt(){
		return deletedAt;
	}

	public String getOrderStatus(){
		return orderStatus;
	}

	public int getOrderRefId(){
		return orderRefId;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public int getTotalAmount(){
		return totalAmount;
	}

	public int getProductId(){
		return productId;
	}

	public int getQty(){
		return qty;
	}

	public String getDealerName(){
		return dealerName;
	}

	public String getSalesmanEmail(){
		return salesmanEmail;
	}

	public int getId(){
		return id;
	}

	public int getSalesPersonId(){
		return salesPersonId;
	}

	public String getOrderId(){
		return orderId;
	}

	public int getSalesmanMobileno(){
		return salesmanMobileno;
	}

	public int getDealerId(){
		return dealerId;
	}

	@Override
 	public String toString(){
		return 
			"DataItem{" + 
			"lr_upload = '" + lrUpload + '\'' + 
			",amount = '" + amount + '\'' + 
			",product_option_title = '" + productOptionTitle + '\'' + 
			",salesman_code = '" + salesmanCode + '\'' + 
			",saleman_name = '" + salemanName + '\'' + 
			",due_date = '" + dueDate + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",tax = '" + tax + '\'' + 
			",deleted_at = '" + deletedAt + '\'' + 
			",order_status = '" + orderStatus + '\'' + 
			",order_ref_id = '" + orderRefId + '\'' + 
			",updated_at = '" + updatedAt + '\'' + 
			",total_amount = '" + totalAmount + '\'' + 
			",product_id = '" + productId + '\'' + 
			",qty = '" + qty + '\'' + 
			",dealer_name = '" + dealerName + '\'' + 
			",salesman_email = '" + salesmanEmail + '\'' + 
			",id = '" + id + '\'' + 
			",sales_person_id = '" + salesPersonId + '\'' + 
			",order_id = '" + orderId + '\'' + 
			",salesman_mobileno = '" + salesmanMobileno + '\'' + 
			",dealer_id = '" + dealerId + '\'' + 
			"}";
		}
}