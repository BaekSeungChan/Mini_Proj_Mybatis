package com.example.minproj2_mybatis.member.entity;

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

    private String email;

    private String password;

    private String address;

    private Role role;

    private String isUsed;

    private String isDel;

//    public static MemberEntity createMember(MemberDTO memberDTO, PasswordEncoder passwordEncoder){
//        return MemberEntity.builder()
//                .name(memberDTO.getName())
//                .email(memberDTO.getEmail())
//                .password(passwordEncoder.encode(memberDTO.getPassword()))
//                .address(memberDTO.getAddress())
//                .role(Role.USER)
//                .build();
//    }

}
