package com.example.minproj2_mybatis.member.entity;

import com.example.minproj2_mybatis.auth.dto.request.MemberJoinRequest;
import com.example.minproj2_mybatis.member.constant.Role;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter @Setter
@ToString
@AllArgsConstructor @NoArgsConstructor
@Builder
public class MemberEntity {

    private Long id;

    private String name;

    private String username;

    private String email;

    private String password;

    private String address;

    private String sex;

    private Role role;

    private String isUsed;

    private String isDel;

    public static MemberEntity createMember(MemberJoinRequest request, PasswordEncoder passwordEncoder){
        return MemberEntity.builder()
                .name(request.getName())
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .address(request.getAddress())
                .sex(request.getSex())
                .role(Role.ROLE_USER)
                .build();
    }

}
