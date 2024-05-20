package com.example.minproj2_mybatis.pay.controller;

import com.example.minproj2_mybatis.pay.entity.PrePaymentEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @PostMapping("/prepare")
    public void preparePayment(@RequestBody PrePaymentEntity request){

    }
}
