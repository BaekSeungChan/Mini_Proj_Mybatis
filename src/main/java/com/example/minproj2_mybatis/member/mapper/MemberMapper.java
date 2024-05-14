package com.example.minproj2_mybatis.member.mapper;

import com.example.minproj2_mybatis.member.entity.MemberEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface MemberMapper {

    int save(MemberEntity member);

    Optional<MemberEntity> findByEmail(String email);

    List<MemberEntity> findAll();

    void delete(Long id);

    Boolean existsfindByEmail(String email);

}
