package com.example.minproj2_mybatis.pay.entity;

import lombok.*;

import java.math.BigDecimal;

@ToString
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PrePaymentEntity {

    private Long id;
    private String merchant_uid;
    BigDecimal amount;
}
