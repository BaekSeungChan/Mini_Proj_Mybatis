package com.example.minproj2_mybatis.member.mapper;

import com.example.minproj2_mybatis.member.entity.MemberEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {

    int save(MemberEntity member);
}
