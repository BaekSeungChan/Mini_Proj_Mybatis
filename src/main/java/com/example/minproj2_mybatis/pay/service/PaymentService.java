package com.example.minproj2_mybatis.pay.service;

import com.example.minproj2_mybatis.pay.entity.PrePaymentEntity;
import com.example.minproj2_mybatis.pay.mapper.PrePaymentMapper;
import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.request.PrepareData;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class PaymentService {

    @Value("${PAY_REST_API_KEY}")
    private String payApiKEy;

    @Value("${PAY_REST_API_SECRET_KEY}")
    private String payApiSecretKey;

    private IamportClient api;

    @Autowired
    private  PrePaymentMapper prePaymentMapper;

    public PaymentService(){
        this.api = new IamportClient(payApiKEy, payApiSecretKey);
    }


    public void postPrepare(PrePaymentEntity request) throws IamportResponseException, IOException {

        PrepareData prepareData = new PrepareData(request.getMerchant_uid(), request.getAmount());
        api.postPrepare(prepareData);

        prePaymentMapper.save(request);
    }
}
