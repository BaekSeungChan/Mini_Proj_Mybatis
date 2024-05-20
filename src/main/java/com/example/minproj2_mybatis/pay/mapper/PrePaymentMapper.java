package com.example.minproj2_mybatis.pay.mapper;

import com.example.minproj2_mybatis.pay.entity.PrePaymentEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PrePaymentMapper {

    void save(PrePaymentEntity request);
}
