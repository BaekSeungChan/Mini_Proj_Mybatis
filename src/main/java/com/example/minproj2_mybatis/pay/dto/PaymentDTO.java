package com.example.minproj2_mybatis.pay.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDTO {
    private String imp_uid;
    private String merchant_uid;
}