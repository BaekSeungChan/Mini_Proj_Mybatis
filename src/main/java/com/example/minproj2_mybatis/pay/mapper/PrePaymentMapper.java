package com.example.minproj2_mybatis.pay.mapper;

import com.example.minproj2_mybatis.pay.entity.PrePaymentEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Optional;

@Mapper
public interface PrePaymentMapper {

    void save(PrePaymentEntity request);

    @Select("SELECT * FROM pre_payment WHERE merchant_uid = #{merchant_uid}")
    Optional<PrePaymentEntity> findById(String merchant_uid);
}
