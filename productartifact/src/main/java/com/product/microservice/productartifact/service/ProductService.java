package com.product.microservice.productartifact.service;

import com.product.microservice.productartifact.dto.MerchantDTO;
import com.product.microservice.productartifact.dto.ProductDTO;
import com.product.microservice.productartifact.entity.MerchantEntity;
import com.product.microservice.productartifact.entity.ProductEntity;

import java.util.List;

public interface ProductService {
    public ProductDTO getProduct(String productId)throws Exception;
    public ProductEntity saveProduct(ProductDTO productDTO);
    public String updateMerchantQuantity(String productId , String merchantId , int quantity);
    public String checkForMerchantQuantity(String productId , String merchantId , int quantity);
    public List<ProductDTO> getAllByCategory(String producttype);
    public ProductDTO addMerchant(String productId, MerchantDTO merchantDTO);
    public List<ProductDTO> getAllProducts();
    public double getpriceFromProdMerchantId(String productId, String merchantId);
    public String getMerchantName(String productId, String merchantId);
    public List<MerchantEntity> getMerchantEntityHaveStockFromProductId(String productId);
    public List<MerchantEntity> getAllMerchantEntityFromProductId(String productId);
    public List<String> getMerchantIdList(String productId);
    public List<Double> getMerchantPriceList(String productId);
    public List<Double> getMerchantReviewList(String productId);

    public  String getImagePath(String productId);
    public String getProductName(String productId);
}
