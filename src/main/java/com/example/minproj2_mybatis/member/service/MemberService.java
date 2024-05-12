package com.example.minproj2_mybatis.member.service;

import com.example.minproj2_mybatis.member.entity.MemberEntity;
import com.example.minproj2_mybatis.member.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService{

    private final MemberMapper memberMapper;

    public int save(MemberEntity member){

        duplicatedforCheck(member);

        return memberMapper.save(member);

    }


    private void duplicatedforCheck(MemberEntity member){
        if (memberMapper.existsfindByEmail(member.getEmail())) {
            throw new IllegalArgumentException("이미 존재하는 아이디 입니다.");
        }
    }
}
