package com.genetic.dealer.model;

public class CustomProductOptionModel {
    private ProductOption productOption;
    private int quantity;

    public CustomProductOptionModel(ProductOption productOption, int quantity) {
        this.productOption = productOption;
        this.quantity = quantity;
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
}
