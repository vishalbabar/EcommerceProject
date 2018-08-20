package com.coviam.project.SpringProject.wrapperclasses;

public class AllCartDetails {
    private String productId;
    private String merchantId;
    private String productName;
    private String merchantName;
    private double price;
    private  String cartId;
    private  int quantity;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AllCartDetails{");
        sb.append("productId='").append(productId).append('\'');
        sb.append(", merchantId='").append(merchantId).append('\'');
        sb.append(", productName='").append(productName).append('\'');
        sb.append(", merchantName='").append(merchantName).append('\'');
        sb.append(", price=").append(price);
        sb.append(", cartId='").append(cartId).append('\'');
        sb.append(", quantity=").append(quantity);
        sb.append('}');
        return sb.toString();
    }
}
