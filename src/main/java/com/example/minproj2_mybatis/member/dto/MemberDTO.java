package com.example.minproj2_mybatis.member.dto;


import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor @NoArgsConstructor
@Builder
public class MemberDTO {

    private Long id;

    private String name;

    private String email;

    private String password;

    private String passwordCheck;

    private String address;

}
