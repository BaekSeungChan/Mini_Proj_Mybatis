package com.example.minproj2_mybatis.member.service;

import com.example.minproj2_mybatis.member.dto.MemberDTO;
import com.example.minproj2_mybatis.member.entity.MemberEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public MemberEntity createMember(){
        MemberDTO memberDTO = MemberDTO.builder()
                .address("경기도 성남시")
                .name("백승찬")
                .email("test@naver.com")
                .password("1234")
                .build();

        MemberEntity member =  MemberEntity.createMember(memberDTO, passwordEncoder);

        return member;
    }


    @Test
    @DisplayName("member 저장")
    public void saveMember(){
        MemberEntity member = createMember();
        System.out.println(member);
        int member1 = memberService.save(member);
        System.out.println(member1);
    }


}