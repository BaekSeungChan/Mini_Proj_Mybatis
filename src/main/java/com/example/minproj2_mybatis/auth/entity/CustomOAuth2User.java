package com.example.minproj2_mybatis.auth.entity;

import com.example.minproj2_mybatis.auth.dto.request.MemberJoinRequest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public class CustomOAuth2User implements OAuth2User {

    private final MemberJoinRequest memberJoinRequest;

    public CustomOAuth2User(MemberJoinRequest memberJoinRequest){
        this.memberJoinRequest = memberJoinRequest;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return null;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();

        collection.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return memberJoinRequest.getRole();
            }
        });

        return collection;
    }

    @Override
    public String getName() {
        return memberJoinRequest.getName();
    }

    public String getUsername(){
        return memberJoinRequest.getUsername();
    }
}
