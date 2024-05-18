package com.example.minproj2_mybatis.member.service;

import com.example.minproj2_mybatis.member.entity.MemberEntity;
import com.example.minproj2_mybatis.member.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberMapper memberMapper;


    public int save(MemberEntity memberDTO){
        validateDuplicationMember(memberDTO);
        return memberMapper.save(memberDTO);
    }

    private void validateDuplicationMember(MemberEntity member){
        Optional<MemberEntity> findMember = memberMapper.findByUsername(member.getName());

        if(findMember.isPresent()){
            throw new IllegalStateException("이미 존재하는 이메일입니다.");
        }
    }
}
