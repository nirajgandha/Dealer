package com.genetic.dealer.model;

import com.google.gson.annotations.SerializedName;

public class PaymentItem {

    @SerializedName("image")
    private String image;

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

    @SerializedName("order_date")
    private String orderDate;

    @SerializedName("updated_at")
    private String updatedAt;

    @SerializedName("total_amount")
    private int totalAmount;

    @SerializedName("sale_man_id")
    private int saleManId;

    @SerializedName("dealer_name")
    private String dealerName;

    @SerializedName("id")
    private int id;

    @SerializedName("order_id")
    private String orderId;

    @SerializedName("payment_date")
    private String paymentDate;

    @SerializedName("dealer_id")
    private int dealerId;

    @SerializedName("status")
    private String status;

    @SerializedName("order_total_amount")
    private int orderTotalAmount;

    public String getImage() {
        return image;
    }

    public String getSalemanName() {
        return salemanName;
    }

    public String getDescription() {
        return description;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getType() {
        return type;
    }

    public String getDeletedAt() {
        return deletedAt;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public int getSaleManId() {
        return saleManId;
    }

    public String getDealerName() {
        return dealerName;
    }

    public int getId() {
        return id;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public int getDealerId() {
        return dealerId;
    }

    public String getStatus() {
        return status;
    }

    public int getOrderTotalAmount() {
        return orderTotalAmount;
    }

    @Override
    public String toString() {
        return
                "DataItem{" +
                        "image = '" + image + '\'' +
                        ",saleman_name = '" + salemanName + '\'' +
                        ",description = '" + description + '\'' +
                        ",created_at = '" + createdAt + '\'' +
                        ",type = '" + type + '\'' +
                        ",deleted_at = '" + deletedAt + '\'' +
                        ",order_date = '" + orderDate + '\'' +
                        ",updated_at = '" + updatedAt + '\'' +
                        ",total_amount = '" + totalAmount + '\'' +
                        ",sale_man_id = '" + saleManId + '\'' +
                        ",dealer_name = '" + dealerName + '\'' +
                        ",id = '" + id + '\'' +
                        ",order_id = '" + orderId + '\'' +
                        ",payment_date = '" + paymentDate + '\'' +
                        ",dealer_id = '" + dealerId + '\'' +
                        ",status = '" + status + '\'' +
                        ",order_total_amount = '" + orderTotalAmount + '\'' +
                        "}";
    }
}