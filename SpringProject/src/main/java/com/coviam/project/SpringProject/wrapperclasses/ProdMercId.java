package com.coviam.project.SpringProject.wrapperclasses;

import java.security.PrivateKey;

public class ProdMercId {

    private String productId;
    private String merchantId;


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ProdMercId{");
        sb.append("productId='").append(productId).append('\'');
        sb.append(", merchantId='").append(merchantId).append('\'');
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
}
