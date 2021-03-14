package com.genetic.dealer.model;

import com.google.gson.annotations.SerializedName;

public class OrderDetailItem{

	@SerializedName("lr_upload")
	private String lrUpload;

	@SerializedName("product_option_title")
	private String productOptionTitle;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("order_status")
	private String orderStatus;

	@SerializedName("order_ref_id")
	private int orderRefId;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("product_id")
	private int productId;

	@SerializedName("salesman_email")
	private String salesmanEmail;

	@SerializedName("id")
	private int id;

	@SerializedName("sales_person_id")
	private int salesPersonId;

	@SerializedName("salesman_mobileno")
	private int salesmanMobileno;

	@SerializedName("dealer_id")
	private int dealerId;

	@SerializedName("amount")
	private int amount;

	@SerializedName("salesman_code")
	private String salesmanCode;

	@SerializedName("saleman_name")
	private String salemanName;

	@SerializedName("due_date")
	private String dueDate;

	@SerializedName("tax")
	private int tax;

	@SerializedName("deleted_at")
	private String deletedAt;

	@SerializedName("product_name")
	private String productName;

	@SerializedName("order_date")
	private String orderDate;

	@SerializedName("total_amount")
	private int totalAmount;

	@SerializedName("qty")
	private int qty;

	@SerializedName("dealer_name")
	private String dealerName;

	@SerializedName("invoice")
	private String invoice;

	@SerializedName("order_id")
	private String orderId;

	public String getLrUpload(){
		return lrUpload;
	}

	public String getProductOptionTitle(){
		return productOptionTitle;
	}

	public String getCreatedAt(){
		return createdAt;
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

	public int getProductId(){
		return productId;
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

	public int getSalesmanMobileno(){
		return salesmanMobileno;
	}

	public int getDealerId(){
		return dealerId;
	}

	public int getAmount(){
		return amount;
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

	public int getTax(){
		return tax;
	}

	public String getDeletedAt(){
		return deletedAt;
	}

	public String getProductName(){
		return productName;
	}

	public String getOrderDate(){
		return orderDate;
	}

	public int getTotalAmount(){
		return totalAmount;
	}

	public int getQty(){
		return qty;
	}

	public String getDealerName(){
		return dealerName;
	}

	public String getInvoice(){
		return invoice;
	}

	public String getOrderId(){
		return orderId;
	}

	@Override
 	public String toString(){
		return 
			"OrderDetailItem{" + 
			"lr_upload = '" + lrUpload + '\'' + 
			",product_option_title = '" + productOptionTitle + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",order_status = '" + orderStatus + '\'' + 
			",order_ref_id = '" + orderRefId + '\'' + 
			",updated_at = '" + updatedAt + '\'' + 
			",product_id = '" + productId + '\'' + 
			",salesman_email = '" + salesmanEmail + '\'' + 
			",id = '" + id + '\'' + 
			",sales_person_id = '" + salesPersonId + '\'' + 
			",salesman_mobileno = '" + salesmanMobileno + '\'' + 
			",dealer_id = '" + dealerId + '\'' + 
			",amount = '" + amount + '\'' + 
			",salesman_code = '" + salesmanCode + '\'' + 
			",saleman_name = '" + salemanName + '\'' + 
			",due_date = '" + dueDate + '\'' + 
			",tax = '" + tax + '\'' + 
			",deleted_at = '" + deletedAt + '\'' + 
			",product_name = '" + productName + '\'' + 
			",order_date = '" + orderDate + '\'' + 
			",total_amount = '" + totalAmount + '\'' + 
			",qty = '" + qty + '\'' + 
			",dealer_name = '" + dealerName + '\'' + 
			",invoice = '" + invoice + '\'' + 
			",order_id = '" + orderId + '\'' + 
			"}";
		}
}