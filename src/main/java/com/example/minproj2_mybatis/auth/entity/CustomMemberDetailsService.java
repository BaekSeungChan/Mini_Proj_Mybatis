package com.example.minproj2_mybatis.auth.entity;

import com.example.minproj2_mybatis.member.entity.MemberEntity;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.ArrayList;
import java.util.Collection;

@ToString
@Slf4j
public class CustomMemberDetailsService implements UserDetails{

    private MemberEntity member;

    public CustomMemberDetailsService(MemberEntity member){
        this.member = member;

        log.info("생성자 {}", this);
    }

//    : MemberEntity(id=43, name=백승찬, username=qortmdcks95, email=bsc7386@likelion.org, password=$2a$10$vlPWP5PjiOytU2JpDpn63eKutCcbLnQYOnhs8ArCE.ej7NODtNEU6, address=경기도 성남시, sex=male, role=ROLE_USER, isUsed=Y, isDel=N)


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {


        Collection<GrantedAuthority> collection = new ArrayList<>();

        collection.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {

                return String.valueOf(member.getRole());
            }
        });

        return collection;
    }

    @Override
    public String getPassword() {
        return member.getPassword();
    }

    @Override
    public String getUsername() {
        return member.getUsername();
    }

    public String getName() {

        return member.getName();
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
