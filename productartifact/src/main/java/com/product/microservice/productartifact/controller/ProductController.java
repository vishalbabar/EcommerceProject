package com.product.microservice.productartifact.controller;


import com.product.microservice.productartifact.dto.MerchantDTO;
import com.product.microservice.productartifact.dto.MerchantProductWrapper;
import com.product.microservice.productartifact.dto.ProductDTO;
import com.product.microservice.productartifact.entity.MerchantEntity;
import com.product.microservice.productartifact.entity.ProductEntity;
import com.product.microservice.productartifact.exceptions.ProductNotFoundException;
import com.product.microservice.productartifact.service.ProductService;
import com.product.microservice.productartifact.wrapperclasses.Cartdetails;
import com.product.microservice.productartifact.wrapperclasses.ProdMercId;
import com.product.microservice.productartifact.wrapperclasses.ProdMerchantIdName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.InvocationTargetException;
import java.net.ConnectException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@CrossOrigin
@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;


    @RequestMapping(method = RequestMethod.GET, value = "/get/{productId}")
    public ResponseEntity<ProductDTO> getProduct(@PathVariable("productId") String productId) throws ProductNotFoundException {
        try {
            ProductDTO productDTO = productService.getProduct(productId);
            return new ResponseEntity<ProductDTO>(productDTO, HttpStatus.OK);
        }catch (Exception e){
                throw new ProductNotFoundException("The product is not found");
        }
//        return new ResponseEntity<ProductDTO>(productDTO, HttpStatus.OK);

    }


    @RequestMapping(method = RequestMethod.POST, value = "/addProduct")
    public ResponseEntity<String> addProduct(@RequestBody ProductDTO productDTO){
        ProductEntity productEntity = new ProductEntity();
        productEntity = productService.saveProduct(productDTO);
        return new ResponseEntity<String>(productEntity.getProductId(), HttpStatus.CREATED);

    }

    @RequestMapping(method = RequestMethod.GET, value = "/updateQuantity/{productId}/{merchantId}/{quantity}")
    public ResponseEntity<Map<String,String>> updateMerchantQuantity(@PathVariable String productId , @PathVariable String merchantId , @PathVariable int quantity){
        String response = productService.updateMerchantQuantity(productId,merchantId,quantity);
//        "{\"success\":1}"
        return new ResponseEntity<Map<String,String>>(Collections.singletonMap("response", response), HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/checkForQuantity/{productId}/{merchantId}/{quantity}")
    public ResponseEntity<Map<String,String>> checkForMerchantQuantity(@PathVariable String productId ,@PathVariable String merchantId ,@PathVariable int quantity){
        String response = productService.checkForMerchantQuantity(productId,merchantId,quantity);
//        "{\"success\":1}"
        return new ResponseEntity<Map<String,String>>(Collections.singletonMap("response", response), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/addmerchant/{productId}")
    public ResponseEntity<ProductDTO> addMerchantInProduct(@PathVariable String productId,@RequestBody MerchantDTO merchantDTO){
        ProductDTO productDTO = productService.addMerchant(productId,merchantDTO);
        return new ResponseEntity<ProductDTO>(productDTO,HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getbycategory/{type}")
    public ResponseEntity<List<ProductDTO>> getProductsByCategory(@PathVariable String type){
        List<ProductDTO> productDTOList = new ArrayList<>();
        productDTOList = productService.getAllByCategory(type);
        return new ResponseEntity<List<ProductDTO>>(productDTOList, HttpStatus.OK);
    }



    @RequestMapping(method = RequestMethod.GET, value = "/getAllProducts")
    public ResponseEntity<List<ProductDTO>> getAllProducts(){
        List<ProductDTO> productDTOList = new ArrayList<>();
        productDTOList = productService.getAllProducts();
        return new ResponseEntity<List<ProductDTO>>(productDTOList, HttpStatus.OK);
    }



    @RequestMapping(method = RequestMethod.GET, value = "/getmerchantprice/{productId}/{merchantId}")
    public ResponseEntity<Double> getpriceFromProdMerchantId(@PathVariable String productId, @PathVariable String merchantId){
        Double price = productService.getpriceFromProdMerchantId(productId,merchantId);
        return new ResponseEntity<Double>(price,HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.GET, value = "/getproductname/{productId}")
    public ResponseEntity<Object> getproductName(@PathVariable String productId){
        String productName = productService.getProductName(productId);
        return new ResponseEntity<Object>(productName,HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getmerchantname/{productId}/{merchantId}")
    public ResponseEntity<String> getMerchantName(@PathVariable String productId, @PathVariable String merchantId){
        String merchantName = productService.getMerchantName(productId,merchantId);
        return new ResponseEntity<String>(merchantName,HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getmerchantentity/{productId}")
    public ResponseEntity<List<MerchantEntity>> getMerchantEntityFromProductId(@PathVariable String productId){
        List<MerchantEntity> merchantEntityList = productService.getAllMerchantEntityFromProductId(productId);
        return new ResponseEntity<List<MerchantEntity>>(merchantEntityList,HttpStatus.OK);
    }



//
    @RequestMapping(method = RequestMethod.GET, value = "/getmerchantwrapperlist/{productId}")
    public ResponseEntity<List<MerchantProductWrapper>> getMerchantwrapperFromProductService(@PathVariable String productId) throws ProductNotFoundException{

        try {
            List<MerchantEntity> merchantEntityList = productService.getMerchantEntityHaveStockFromProductId(productId);
            List<MerchantProductWrapper> merchantProductWrapperList = new ArrayList<>();
            String urlPost = "http://localhost:8081/merchant/getMerchantRanks";

            RestTemplate restTemplate = new RestTemplate();
            merchantProductWrapperList = restTemplate.postForObject(urlPost, merchantEntityList, List.class);
            return new ResponseEntity<List<MerchantProductWrapper>>(merchantProductWrapperList, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(e);
            throw new ProductNotFoundException("Error in getting merchants list");
        }


    }


    @RequestMapping(method = RequestMethod.GET, value = "/getmerchantidlist/{productId}")
    public ResponseEntity<List<String>> getMerchantIdList(@PathVariable String productId){
        List<String> merchantIdList = productService.getMerchantIdList(productId);
        return new ResponseEntity<List<String>>(merchantIdList,HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getmerchantpricelist/{productId}")
    public ResponseEntity<List<Double>> getMerchantPriceList(@PathVariable String productId){
        List<Double> merchantPriceList = productService.getMerchantPriceList(productId);
        return new ResponseEntity<List<Double>>(merchantPriceList,HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getmerchantreviewlist/{productId}")
    public ResponseEntity<List<Double>> getMerchantReviewList(@PathVariable String productId){

        List<Double> merchantReviewList = productService.getMerchantReviewList(productId);
        return new ResponseEntity<List<Double>>(merchantReviewList,HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.GET, value = "/getimagepath/{productId}")
    public ResponseEntity<String> getImagePath(@PathVariable  String productId){
        return  new ResponseEntity<String>(productService.getImagePath(productId),HttpStatus.OK);
    }



    @RequestMapping(method = RequestMethod.POST, value = "/getcartdetails")
    public ResponseEntity<List<Cartdetails>> getCartDetails(@RequestBody List<ProdMercId> prodMercIdList){
        List<Cartdetails> cartdetailsList = new ArrayList<>();
        prodMercIdList.forEach(prodMercId -> {
            Cartdetails cartdetails = new Cartdetails();
            String merchantName = productService.getMerchantName(prodMercId.getProductId(),prodMercId.getMerchantId());
            double price = productService.getpriceFromProdMerchantId(prodMercId.getProductId(),prodMercId.getMerchantId());
            String produceName = productService.getProductName(prodMercId.getProductId());

            cartdetails.setMerchantId(prodMercId.getMerchantId());
            cartdetails.setProductId(prodMercId.getProductId());
            cartdetails.setProductName(produceName);
            cartdetails.setMerchantName(merchantName);
            cartdetails.setPrice(price);
            cartdetailsList.add(cartdetails);
        });

        return new ResponseEntity<List<Cartdetails>>(cartdetailsList,HttpStatus.OK);
    }



    @RequestMapping(method = RequestMethod.GET, value = "/getproductmerchantnames/{productId}/{merchantId}")
    public ResponseEntity<ProdMerchantIdName> getProdMercNames(@PathVariable String productId, @PathVariable String merchantId){
        ProdMerchantIdName prodMerchantIdName = new ProdMerchantIdName();
        String merchantName = productService.getMerchantName(productId,merchantId);
        String productName  = productService.getProductName(productId);
        prodMerchantIdName.setMerchantId(merchantId);
        prodMerchantIdName.setMerchantName(merchantName);
        prodMerchantIdName.setProductId(productId);
        prodMerchantIdName.setProductName(productName);
        return new ResponseEntity<ProdMerchantIdName>(prodMerchantIdName,HttpStatus.OK);
    }




}
