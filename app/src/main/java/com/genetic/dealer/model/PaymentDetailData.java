package com.genetic.dealer.model;

import java.util.ArrayList;
import com.google.gson.annotations.SerializedName;

public class PaymentDetailData {

	@SerializedName("payment_detail")
	private PaymentDetail paymentDetail;

	@SerializedName("order_detail")
	private ArrayList<OrderDetailItem> orderDetail;

	public PaymentDetail getPaymentDetail(){
		return paymentDetail;
	}

	public ArrayList<OrderDetailItem> getOrderDetail(){
		return orderDetail;
	}

	@Override
 	public String toString(){
		return 
			"Data{" + 
			"payment_detail = '" + paymentDetail + '\'' + 
			",order_detail = '" + orderDetail + '\'' + 
			"}";
		}
}