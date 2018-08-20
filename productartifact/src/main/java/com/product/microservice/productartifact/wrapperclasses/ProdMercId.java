package com.product.microservice.productartifact.wrapperclasses;

public class ProdMercId {

    private String productId;
    private String merchantId;

    @Override
    public String toString() {
        return "ProdMercId{" +
                "productId='" + productId + '\'' +
                ", merchantId='" + merchantId + '\'' +
                '}';
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
}
