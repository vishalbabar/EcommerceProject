package com.coviam.project.SpringProject.wrapperclasses;

public class Cartdetails {
    private String productId;
    private String merchantId;
    private String productName;
    private String merchantName;
    private double price;


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Cartdetails{");
        sb.append("productId='").append(productId).append('\'');
        sb.append(", merchantId='").append(merchantId).append('\'');
        sb.append(", productName='").append(productName).append('\'');
        sb.append(", merchantName='").append(merchantName).append('\'');
        sb.append(", price=").append(price);

        sb.append('}');
        return sb.toString();
    }



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
}
