package com.genetic.dealer.model;

import com.google.gson.annotations.SerializedName;

public  class DashboardOrderListItem {

	@SerializedName("lr_upload")
	private String lrUpload;

	@SerializedName("product_name")
	private String productName;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("order_status")
	private String orderStatus;

	@SerializedName("order_tax_amount")
	private double orderTaxAmount;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("salesman_email")
	private String salesmanEmail;

	@SerializedName("id")
	private int id;

	@SerializedName("sales_person_id")
	private int salesPersonId;

	@SerializedName("salesman_mobileno")
	private String salesmanMobileno;

	@SerializedName("dealer_id")
	private int dealerId;

	@SerializedName("order_total_amount")
	private double orderTotalAmount;

	@SerializedName("salesman_code")
	private String salesmanCode;

	@SerializedName("saleman_name")
	private String salemanName;

	@SerializedName("due_date")
	private String dueDate;

	@SerializedName("tax")
	private double tax;

	@SerializedName("deleted_at")
	private String deletedAt;

	@SerializedName("total_amount")
	private double totalAmount;

	@SerializedName("dealer_name")
	private String dealerName;

	@SerializedName("invoice")
	private String invoice;

	@SerializedName("order_id")
	private String orderId;

	public String getLrUpload(){
		return lrUpload;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public String getOrderStatus(){
		return orderStatus;
	}

	public double getOrderTaxAmount(){
		return orderTaxAmount;
	}

	public String getUpdatedAt(){
		return updatedAt;
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

	public String getSalesmanMobileno(){
		return salesmanMobileno;
	}

	public int getDealerId(){
		return dealerId;
	}

	public double getOrderTotalAmount(){
		return orderTotalAmount;
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

	public double getTax(){
		return tax;
	}

	public String getDeletedAt(){
		return deletedAt;
	}

	public double getTotalAmount(){
		return totalAmount;
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

	public String getProductName() {
		return productName;
	}

	@Override
 	public String toString(){
		return 
			"OrderListItem{" + 
			"lr_upload = '" + lrUpload + '\'' +
			",created_at = '" + createdAt + '\'' + 
			",order_status = '" + orderStatus + '\'' + 
			",order_tax_amount = '" + orderTaxAmount + '\'' +
			",updated_at = '" + updatedAt + '\'' +
			",salesman_email = '" + salesmanEmail + '\'' + 
			",id = '" + id + '\'' + 
			",sales_person_id = '" + salesPersonId + '\'' + 
			",salesman_mobileno = '" + salesmanMobileno + '\'' + 
			",dealer_id = '" + dealerId + '\'' + 
			",order_total_amount = '" + orderTotalAmount + '\'' +
			",salesman_code = '" + salesmanCode + '\'' + 
			",saleman_name = '" + salemanName + '\'' + 
			",due_date = '" + dueDate + '\'' + 
			",tax = '" + tax + '\'' + 
			",deleted_at = '" + deletedAt + '\'' + 
			",total_amount = '" + totalAmount + '\'' +
			",dealer_name = '" + dealerName + '\'' + 
			",invoice = '" + invoice + '\'' + 
			",order_id = '" + orderId + '\'' + 
			",product_name = '" + productName + '\'' +
			"}";
		}
}