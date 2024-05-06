package com.example.minproj2_mybatis.config;

import com.example.minproj2_mybatis.member.entity.MemberEntity;
import com.example.minproj2_mybatis.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class MemberAuthenticatorProvider implements AuthenticationProvider {

    @Autowired
    private MemberService memberService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String userEmail = authentication.getName();
        String password = (String) authentication.getCredentials();


        MemberPrincipalDetails memberPrincipalDetails = (MemberPrincipalDetails) memberService.loadUserByUsername(userEmail);

        String dbPassword = memberPrincipalDetails.getPassword();

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


        if(!passwordEncoder.matches(password, dbPassword)){
            System.out.println("비밀번호가 일치하지 않습니다.");
            throw new BadCredentialsException("아이디 또는 비밀번호가 일칳라지 않습니다.");
        }

        MemberEntity member = memberPrincipalDetails.getMember();
        if(member == null || "N".equals(member.getIsUsed())){
            throw new BadCredentialsException("사용할 수 없는 계정입니다.");
        }

        return new UsernamePasswordAuthenticationToken(memberPrincipalDetails, null, memberPrincipalDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
