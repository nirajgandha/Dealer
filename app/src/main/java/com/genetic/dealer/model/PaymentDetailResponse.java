package com.genetic.dealer.model;

import com.google.gson.annotations.SerializedName;

public class PaymentDetailResponse{

	@SerializedName("data")
	private PaymentDetailData paymentDetailData;

	@SerializedName("meta")
	private Meta meta;

	public PaymentDetailData getPaymentDetailData(){
		return paymentDetailData;
	}

	public Meta getMeta(){
		return meta;
	}

	@Override
 	public String toString(){
		return 
			"PaymentDetailResponse{" + 
			"data = '" + paymentDetailData + '\'' +
			",meta = '" + meta + '\'' + 
			"}";
		}
}