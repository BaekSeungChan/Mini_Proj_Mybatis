package com.example.minproj2_mybatis.auth.service;

import com.example.minproj2_mybatis.auth.dto.request.MemberJoinRequest;
import com.example.minproj2_mybatis.auth.dto.response.GoogleResponse;
import com.example.minproj2_mybatis.auth.dto.response.NaverResponse;
import com.example.minproj2_mybatis.auth.dto.response.OAuth2Response;
import com.example.minproj2_mybatis.auth.entity.CustomMemberDetailsService;
import com.example.minproj2_mybatis.auth.entity.CustomOAuth2User;
import com.example.minproj2_mybatis.member.constant.Role;
import com.example.minproj2_mybatis.member.entity.MemberEntity;
import com.example.minproj2_mybatis.member.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final MemberMapper memberMapper;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(userRequest);

        System.out.println("oAuth2User :  " + oAuth2User);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();

        OAuth2Response oAuth2Response = null;

        if(registrationId.equals("naver")) {
            oAuth2Response = new NaverResponse(oAuth2User.getAttributes());
        } else if (registrationId.equals("google")) {
            oAuth2Response = new GoogleResponse(oAuth2User.getAttributes());
        }
        else {
            return null;
        }


        String username = oAuth2Response.getProvider() + " " + oAuth2Response.getProviderId();
        Optional<MemberEntity> existMember = memberMapper.findByUsername(username);

        if(existMember.isEmpty()){
            MemberEntity member = MemberEntity.builder()
                    .username(username)
                    .email(oAuth2Response.getEmail())
                    .name(oAuth2Response.getName())
                    .role(Role.ROLE_USER)
                    .build();

            memberMapper.save(member);

            MemberJoinRequest memberJoinRequest = MemberJoinRequest.builder()
                    .username(username)
                    .name(oAuth2Response.getName())
                    .role("ROLE_USER")
                    .build();

            return new CustomOAuth2User(memberJoinRequest);
        } else {
            MemberEntity member = MemberEntity.builder()
                    .username(existMember.get().getUsername())
                    .name(oAuth2Response.getName())
                    .role(existMember.get().getRole())
                    .build();

            memberMapper.memberUpdate(member);

            MemberJoinRequest memberJoinRequest = MemberJoinRequest.builder()
                    .username(existMember.get().getUsername())
                    .name(oAuth2Response.getName())
                    .role(String.valueOf(Role.ROLE_USER))
                    .build();

            return new CustomOAuth2User(memberJoinRequest);

        }
    }
}
