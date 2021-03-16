package com.genetic.dealer.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class PaymentDueItem{

	@SerializedName("payment_detail")
	private List<PaymentDetailItem> paymentDetail;

	@SerializedName("order_detail")
	private List<OrderDetailItem> orderDetail;

	public List<PaymentDetailItem> getPaymentDetail(){
		return paymentDetail;
	}

	public List<OrderDetailItem> getOrderDetail(){
		return orderDetail;
	}

	@Override
 	public String toString(){
		return 
			"PaymentDueItem{" + 
			"payment_detail = '" + paymentDetail + '\'' + 
			",order_detail = '" + orderDetail + '\'' + 
			"}";
		}
}