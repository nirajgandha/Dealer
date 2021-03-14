package com.genetic.dealer.model;

public class CustomProductOptionModel {
    private ProductOption productOption;
    private int quantity;
    private String imageUrl;

    public CustomProductOptionModel(ProductOption productOption, int quantity, String imageUrl) {
        this.productOption = productOption;
        this.quantity = quantity;
        this.imageUrl = imageUrl;
    }

    public ProductOption getProductOption() {
        return productOption;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setProductOption(ProductOption productOption) {
        this.productOption = productOption;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
