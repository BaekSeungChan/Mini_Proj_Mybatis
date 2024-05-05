package com.example.minproj2_mybatis.member.service;

import com.example.minproj2_mybatis.member.dto.MemberDTO;
import com.example.minproj2_mybatis.member.entity.MemberEntity;
import com.example.minproj2_mybatis.member.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberMapper memberMapper;


    public int save(MemberEntity memberDTO){
        return memberMapper.save(memberDTO);
    }

}
