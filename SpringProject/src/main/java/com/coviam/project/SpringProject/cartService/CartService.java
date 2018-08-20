package com.coviam.project.SpringProject.cartService;

import com.coviam.project.SpringProject.cartDTO.CartDTO;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface CartService {



    public List<CartDTO> getAllCartDetails();
    public CartDTO addToCart(CartDTO cartDTO);
    public Boolean updateCart(String cartid,int quantity);
    public Boolean removeFromCart(String cartid);
    public void removeAllFromCart(String userid);
    public Boolean existsproductname(String productid);
    public Boolean existsuserid(String userid);
    public Boolean existscartid(String cartid);
    public Boolean existsmerchantname(String merchantid);
    public List<CartDTO> getAllUserCartDetails(String userid);


}
