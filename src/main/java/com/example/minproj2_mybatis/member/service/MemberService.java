package com.example.minproj2_mybatis.member.service;

import com.example.minproj2_mybatis.member.dto.MemberDTO;
import com.example.minproj2_mybatis.member.entity.MemberEntity;
import com.example.minproj2_mybatis.member.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {

    private final MemberMapper memberMapper;


    public int save(MemberEntity memberDTO){
        validateDuplicationMember(memberDTO);
        return memberMapper.save(memberDTO);
    }


    private void validateDuplicationMember(MemberEntity member){
        Optional<MemberEntity> findMember = memberMapper.findByEmail(member.getEmail());

        if(findMember.isPresent()){
            throw new IllegalStateException("이미 존재하는 이메일입니다.");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        MemberEntity member = memberMapper.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("해당 사용자가 없습니다."));

        return User.builder()
                .username(member.getName())
                .password(member.getPassword())
                .roles(member.getRole().toString())
                .build();
    }

}
