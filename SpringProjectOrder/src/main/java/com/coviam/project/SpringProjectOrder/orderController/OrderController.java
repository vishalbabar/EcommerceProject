package com.coviam.project.SpringProjectOrder.orderController;

import com.coviam.project.SpringProjectOrder.orderDTO.OrderDTO;
import com.coviam.project.SpringProjectOrder.orderService.EmailService;
import com.coviam.project.SpringProjectOrder.orderService.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    @Qualifier("com.coviam.project.SpringProjectOrder.orderService.OrderServiceImpl")
    OrderService orderService;
    @Autowired

    MailSender mailSender;

    @RequestMapping(method = RequestMethod.GET,value = "/allorderitems")
   public ResponseEntity<List<OrderDTO>> allorderitems(){

        List<OrderDTO> orderDTOList=orderService.allorderitems();
        return new ResponseEntity<List<OrderDTO>>(orderDTOList,HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.POST,value = "/buyall")
    public ResponseEntity<String> buyall(@RequestBody List<OrderDTO> orders){

        String status="success";
        for(OrderDTO order : orders){
            orderService.addToCart(order);
        }
        return new ResponseEntity<String>(status,HttpStatus.OK);

    }


    @RequestMapping(method = RequestMethod.POST,value = "/addorder")
    public ResponseEntity<String> addorder(@RequestBody OrderDTO orderDTO)
    {
        String orderStatus="Ordered Successfully";
        orderService.addToCart(orderDTO);
        return new ResponseEntity<String>(orderStatus,HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.GET,value = "/userorderitems/{userid}")
    public ResponseEntity<List<OrderDTO>> userorderitems(@PathVariable String userid){

        List<OrderDTO> orderDTOList=orderService.userorderitems(userid);

        return new ResponseEntity<List<OrderDTO>>(orderDTOList,HttpStatus.OK);
    }


    @Autowired
    EmailService emailService;
    @RequestMapping(method = RequestMethod.GET,value = "/sendemail/{toEmail}/{subject}/{message}")
    public ResponseEntity<String> emailsend(@PathVariable String toEmail, @PathVariable String subject, @PathVariable String message){

        if(emailService.sendMail(toEmail,subject,message))
            return new ResponseEntity<String>("Email Sent",HttpStatus.OK);
        else return new ResponseEntity<String>("Email Failed",HttpStatus.OK);
    }




    @RequestMapping(method = RequestMethod.GET,value = "/sendemailprd/{toEmail}/{subject}/{message}/{ProductName}/{MerchantName}/{price}/{quantity}/{address}/{city}/{pincode}")
    public ResponseEntity<String> sendMailPrd(@PathVariable String toEmail, @PathVariable String subject, @PathVariable String message,@PathVariable String ProductName,@PathVariable String MerchantName,@PathVariable int price,@PathVariable int quantity,@PathVariable String address,@PathVariable String city,@PathVariable String pincode){

        if(emailService.sendMailPrd(toEmail,subject,message,ProductName,MerchantName,price,quantity,address,city,pincode))
            return new ResponseEntity<String>("Email Sent",HttpStatus.OK);
        else return new ResponseEntity<String>("Email Failed",HttpStatus.OK);
    }

}
