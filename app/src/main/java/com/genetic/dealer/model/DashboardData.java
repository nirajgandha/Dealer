package com.genetic.dealer.model;

import java.util.ArrayList;
import com.google.gson.annotations.SerializedName;

public class DashboardData {

	@SerializedName("recent_order")
	private RecentOrder recentOrder;

	@SerializedName("payment_history")
	private ArrayList<PaymentHistoryItem> paymentHistory;

	@SerializedName("payment_due")
	private ArrayList<PaymentDueItem> paymentDue;

	public RecentOrder getRecentOrder(){
		return recentOrder;
	}

	public ArrayList<PaymentHistoryItem> getPaymentHistory(){
		return paymentHistory;
	}

	public ArrayList<PaymentDueItem> getPaymentDue(){
		return paymentDue;
	}

	@Override
 	public String toString(){
		return 
			"Data{" + 
			"recent_order = '" + recentOrder + '\'' + 
			",payment_history = '" + paymentHistory + '\'' + 
			",payment_due = '" + paymentDue + '\'' + 
			"}";
		}
}