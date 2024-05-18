package com.example.minproj2_mybatis.member.mapper;

import com.example.minproj2_mybatis.member.entity.MemberEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface MemberMapper {

    int save(MemberEntity member);

    Optional<MemberEntity> findByUsername(String username);

    List<MemberEntity> findAll();

    void delete(Long id);

    int memberUpdate(MemberEntity member);

    Boolean existsfindByEmail(String email);

}
