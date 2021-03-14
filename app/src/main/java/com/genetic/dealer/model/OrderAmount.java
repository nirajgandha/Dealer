package com.genetic.dealer.model;

import com.google.gson.annotations.SerializedName;

public class OrderAmount{

	@SerializedName("delivery_charges")
	private double deliveryCharges;

	@SerializedName("sub_total")
	private double subTotal;

	@SerializedName("discount")
	private double discount;

	@SerializedName("tax")
	private double tax;

	@SerializedName("order_total_amount")
	private double orderTotalAmount;

	public double getDeliveryCharges(){
		return deliveryCharges;
	}

	public double getSubTotal(){
		return subTotal;
	}

	public double getDiscount(){
		return discount;
	}

	public double getTax(){
		return tax;
	}

	public double getOrderTotalAmount(){
		return orderTotalAmount;
	}

	@Override
 	public String toString(){
		return 
			"OrderAmount{" + 
			"delivery_charges = '" + deliveryCharges + '\'' + 
			",sub_total = '" + subTotal + '\'' + 
			",discount = '" + discount + '\'' + 
			",tax = '" + tax + '\'' + 
			",order_total_amount = '" + orderTotalAmount + '\'' + 
			"}";
		}
}