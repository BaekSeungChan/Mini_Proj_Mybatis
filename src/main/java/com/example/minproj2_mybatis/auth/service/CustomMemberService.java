package com.example.minproj2_mybatis.auth.service;

import com.example.minproj2_mybatis.auth.entity.CustomMemberDetailsService;
import com.example.minproj2_mybatis.member.entity.MemberEntity;
import com.example.minproj2_mybatis.member.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomMemberService implements UserDetailsService {

    private final MemberMapper memberMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MemberEntity member = memberMapper.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("해당 사용자가 없습니다."));

        System.out.println("username : " + username);
        System.out.println("member : " + member);

        if(member != null){
            return  new CustomMemberDetailsService(member);
        }

        return null;
    }
}
