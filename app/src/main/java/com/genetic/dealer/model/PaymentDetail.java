package com.genetic.dealer.model;

import com.google.gson.annotations.SerializedName;

public class PaymentDetail{

	@SerializedName("image")
	private String image;

	@SerializedName("salesman_code")
	private String salesmanCode;

	@SerializedName("saleman_name")
	private String salemanName;

	@SerializedName("description")
	private String description;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("type")
	private String type;

	@SerializedName("deleted_at")
	private String deletedAt;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("total_amount")
	private int totalAmount;

	@SerializedName("sale_man_id")
	private int saleManId;

	@SerializedName("dealer_name")
	private String dealerName;

	@SerializedName("salesman_email")
	private String salesmanEmail;

	@SerializedName("id")
	private int id;

	@SerializedName("order_id")
	private int orderId;

	@SerializedName("salesman_mobileno")
	private int salesmanMobileno;

	@SerializedName("payment_date")
	private String paymentDate;

	@SerializedName("dealer_id")
	private int dealerId;

	@SerializedName("status")
	private String status;

	public String getImage(){
		return image;
	}

	public String getSalesmanCode(){
		return salesmanCode;
	}

	public String getSalemanName(){
		return salemanName;
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

	public String getUpdatedAt(){
		return updatedAt;
	}

	public int getTotalAmount(){
		return totalAmount;
	}

	public int getSaleManId(){
		return saleManId;
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

	public int getOrderId(){
		return orderId;
	}

	public int getSalesmanMobileno(){
		return salesmanMobileno;
	}

	public String getPaymentDate(){
		return paymentDate;
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
			"PaymentDetail{" + 
			"image = '" + image + '\'' + 
			",salesman_code = '" + salesmanCode + '\'' + 
			",saleman_name = '" + salemanName + '\'' + 
			",description = '" + description + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",type = '" + type + '\'' + 
			",deleted_at = '" + deletedAt + '\'' + 
			",updated_at = '" + updatedAt + '\'' + 
			",total_amount = '" + totalAmount + '\'' + 
			",sale_man_id = '" + saleManId + '\'' + 
			",dealer_name = '" + dealerName + '\'' + 
			",salesman_email = '" + salesmanEmail + '\'' + 
			",id = '" + id + '\'' + 
			",order_id = '" + orderId + '\'' + 
			",salesman_mobileno = '" + salesmanMobileno + '\'' + 
			",payment_date = '" + paymentDate + '\'' + 
			",dealer_id = '" + dealerId + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}