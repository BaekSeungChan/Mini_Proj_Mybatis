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

//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        MemberEntity member = memberMapper.findByEmail(email)
//                .orElseThrow(() -> new UsernameNotFoundException("해당 사용자가 없습니다."));
//
//        System.out.println(member);
//
//        if(member == null){
//            throw new UsernameNotFoundException(email + "을 찾을 수 없습니다.");
//        }
//
//        if(!"Y".equals(member.getIsUsed())){
//            throw new UsernameNotFoundException("사용할 수 없는 계정입니다.");
//        }
//
//        if(!"N".equals(member.getIsDel())){
//            throw new UsernameNotFoundException("삭제된 계정입니다.");
//        }
//
//        return null;
//    }

}
