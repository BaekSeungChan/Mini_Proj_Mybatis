package com.example.minproj2_mybatis.pay.service;

import com.example.minproj2_mybatis.pay.dto.PaymentDTO;
import com.example.minproj2_mybatis.pay.entity.PrePaymentEntity;
import com.example.minproj2_mybatis.pay.mapper.PrePaymentMapper;
import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.request.CancelData;
import com.siot.IamportRestClient.request.PrepareData;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class PaymentService {

    @Value("${PAY_REST_API_KEY}")
    private String payApiKey;

    @Value("${PAY_REST_API_SECRET_KEY}")
    private String payApiSecretKey;

    private IamportClient api;

    private final PrePaymentMapper prePaymentMapper;

    @PostConstruct
    private void init() {
        this.api = new IamportClient(payApiKey, payApiSecretKey);
    }

    public void postPrepare(PrePaymentEntity request) throws IamportResponseException, IOException {
        PrepareData prepareData = new PrepareData(request.getMerchant_uid(), request.getAmount());
        api.postPrepare(prepareData);
        prePaymentMapper.save(request);
    }

    public Payment validatePayment(PaymentDTO request) throws IamportResponseException, IOException {
        PrePaymentEntity prePayment = prePaymentMapper.findById(request.getMerchant_uid())
                .orElseThrow(() -> new IllegalArgumentException("Merchant UID not found"));
        BigDecimal preAmount = prePayment.getAmount(); // DB에 저장된 결제요청 금액

        IamportResponse<Payment> iamportResponse = api.paymentByImpUid(request.getImp_uid());
        BigDecimal paidAmount = iamportResponse.getResponse().getAmount(); // 사용자가 실제 결제한 금액

        if (!preAmount.equals(paidAmount)) {
            CancelData cancelData = cancelPayment(iamportResponse);
            api.cancelPaymentByImpUid(cancelData);
        }

        return iamportResponse.getResponse();
    }

    public CancelData cancelPayment(IamportResponse<Payment> response) {
        return new CancelData(response.getResponse().getImpUid(), true);
    }
}
