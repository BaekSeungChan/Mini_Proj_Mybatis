package com.example.minproj2_mybatis.pay.controller;

import com.example.minproj2_mybatis.pay.dto.PaymentDTO;
import com.example.minproj2_mybatis.pay.entity.PrePaymentEntity;
import com.example.minproj2_mybatis.pay.service.PaymentService;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.response.Payment;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
@Slf4j
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/prepare")
    public ResponseEntity<Map<String, String>> preparePayment(@RequestBody PrePaymentEntity request) throws IamportResponseException, IOException {

        paymentService.postPrepare(request);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Success");

        return ResponseEntity.ok(response);
    }

    @PostMapping("/validate")
    public Payment validatePayment(@RequestBody PaymentDTO request)
            throws IamportResponseException, IOException {
        return paymentService.validatePayment(request);
    }
}
