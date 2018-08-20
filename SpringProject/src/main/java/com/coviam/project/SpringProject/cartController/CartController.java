package com.coviam.project.SpringProject.cartController;
import com.coviam.project.SpringProject.cartDTO.CartDTO;
import com.coviam.project.SpringProject.cartService.CartService;
import com.coviam.project.SpringProject.wrapperclasses.AllCartDetails;
import com.coviam.project.SpringProject.wrapperclasses.Cartdetails;
import com.coviam.project.SpringProject.wrapperclasses.ProdMercId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/cart")
@SessionAttributes("shoppingCart")
public class CartController {

    @Autowired
    @Qualifier("com.coviam.project.SpringProject.cartService.Impl.CartServiceImpl")
    CartService cartService;


    @RequestMapping(method = RequestMethod.GET,value = "/allcartitems")
    public ResponseEntity<List<CartDTO>> getAllCartDetails(){
        List<CartDTO> cartDTOList=cartService.getAllCartDetails();
        return new ResponseEntity<List<CartDTO>>(cartDTOList,HttpStatus.OK);
    }

//    @RequestMapping(method = RequestMethod.POST,value = "/addtocart")
//    public ResponseEntity<String> addtoCart(@RequestBody CartDTO cartDTO){
//        String addStatus="Added Successfully to cart";
//        String wrongStatus="its already in cart";
//
//        if((cartService.existsproductname(cartDTO.getProductid()))&&(cartService.existsmerchantname(cartDTO.getMerchantid())&&(cartService.existsuserid(cartDTO.getUserid())))){
//            return new ResponseEntity<String>(wrongStatus,HttpStatus.OK);
//        }
//        CartDTO cartDTOadd=cartService.addToCart(cartDTO);
//
//        return new ResponseEntity<String>(cartDTOadd.getCartid(), HttpStatus.OK);
//    }



    @RequestMapping(method = RequestMethod.POST,value = "/addtocart")
    public ResponseEntity<String> addtoCart(@RequestBody CartDTO cartDTO){
        int status =0;
        String addStatus="Added Successfully to cart";
        String wrongStatus="its already in cart";
        List<CartDTO> cartDTOList=cartService.getAllUserCartDetails(cartDTO.getUserid());
        for(int i=0;i<cartDTOList.size();i++){
            if(cartDTOList.get(i).getProductid().equals(cartDTO.getProductid())&&cartDTOList.get(i).getMerchantid().equals(cartDTO.getMerchantid())){
                return new ResponseEntity<String>(wrongStatus, HttpStatus.OK);
            }
//
//            if(cartDTOList.get(i).getCartid().equals(cartDTO.getCartid())){
//                cartService.updateCart(cartDTO.getCartid(),cartDTO.getQuantity());
//
//            }
        }
        CartDTO cartDTOadd=cartService.addToCart(cartDTO);
        return new ResponseEntity<String>(cartDTOadd.getCartid(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET,value = "/usercartitems/{userid}")
    public ResponseEntity<List<CartDTO>> getAllUserCartDetails(@PathVariable String userid){
        List<CartDTO> cartDTOList=cartService.getAllUserCartDetails(userid);
        return new ResponseEntity<List<CartDTO>>(cartDTOList,HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST,value = "/updatetocart/{cartid}/{quantity}")
    public ResponseEntity<String> updateToCart(@PathVariable String cartid,@PathVariable int quantity){
        String updatedStatus="Updated Successfully";
        String cartStatus="no cart id";
        String updatedNotStatus="not Updated Successfully";
        if(cartService.existscartid(cartid)) {

            if (cartService.updateCart(cartid, quantity)) {
                return new ResponseEntity<String>(updatedStatus, HttpStatus.OK);
            }

            return new ResponseEntity<String>(updatedNotStatus, HttpStatus.OK);

        }
        return new ResponseEntity<String>(cartStatus,HttpStatus.OK);

    }



    @RequestMapping(method = RequestMethod.GET,value = "/removefromcart/{cartid}")
    public ResponseEntity<String> deleteFromCart(@PathVariable String cartid){
        String deletedStatus="deleted Successfully from cart";
        String deletedNotStatus="not deleted successfully from cart";
        if(cartService.removeFromCart(cartid)!=false){
            return new ResponseEntity<String>(deletedStatus,HttpStatus.OK);
        }
        else {
            return new ResponseEntity<String>(deletedNotStatus,HttpStatus.OK);
        }
    }

    @RequestMapping(method = RequestMethod.GET,value = "/removeallfromcart/{userid}")
    public ResponseEntity<String> deleteAllfromCart(@PathVariable String userid){
        String deletedStatus="deleted Successfully from cart";
        String deletedNotStatus="not deleted successfully from cart";
//        if(cartService.removeAllFromCart(userid)){
//            return new ResponseEntity<String>(deletedStatus,HttpStatus.OK);
//        }
//        else {
//            return new ResponseEntity<String>(deletedNotStatus,HttpStatus.OK);
//        }
        cartService.removeAllFromCart(userid);
        return new ResponseEntity<String>(deletedStatus,HttpStatus.OK);

    }


    @RequestMapping(method = RequestMethod.GET,value = "/usercartitemswithname/{userid}")
    public ResponseEntity<List<Cartdetails>> getAllUserCartWithName(@PathVariable String userid){
        List<CartDTO> cartDTOList=cartService.getAllUserCartDetails(userid);
        List<Cartdetails> cartdetailsList=new ArrayList<>();

        List<ProdMercId> prodMercIds = new ArrayList<>();

        cartDTOList.forEach(cartDTO -> {
            ProdMercId prodMercId = new ProdMercId();
            prodMercId.setMerchantId(cartDTO.getMerchantid());
            prodMercId.setProductId(cartDTO.getProductid());prodMercIds.add(prodMercId);
        });


            RestTemplate restTemplate =new RestTemplate();
            String url = "http://10.177.2.81:8080/product/getcartdetails";
            cartdetailsList = restTemplate.postForObject(url,prodMercIds,List.class);

        return new ResponseEntity<List<Cartdetails>>(cartdetailsList,HttpStatus.OK);
    }





}
