package com.coviam.project.SpringProjectOrder.orderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

/**
 * Created by kiranreddy on 22/04/17.
 */
@Service
public class EmailService {
    @Autowired
    private JavaMailSender javaMailSender;


    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public boolean sendMail(String toEmail, String subject, String message) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(toEmail);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);
        mailMessage.setText(message);

        mailMessage.setFrom("atuljainatuljain1@gmail.com");
        javaMailSender.send(mailMessage);
        return true;
    }

    public boolean sendMailPrd(String toEmail, String subject, String message,String ProductName,String MerchantName,int price,int quantity,String address,String city,String pincode) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setTo(toEmail);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);
        mailMessage.setText(message);





        mailMessage.setText(
                "Dear thank you for placing order "
                        +"\nPRODUCT NAME:"
                        + ProductName +"\nMERCHANT NAME:"

                        + MerchantName +"\nPRICE:"
                +price+"\nQUANTITY:"
                +quantity+ "\nCITY:"
                        +city+"\nPINCODE:"
                        +pincode);



        mailMessage.setFrom("atuljainatuljain1@gmail.com");
        javaMailSender.send(mailMessage);
        return true;
    }
}
