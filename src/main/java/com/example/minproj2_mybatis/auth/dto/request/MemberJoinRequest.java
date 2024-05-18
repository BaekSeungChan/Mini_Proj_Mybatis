package com.example.minproj2_mybatis.auth.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberJoinRequest {

    private Long id;

    @NotBlank(message = "사용자 아이디는 필수입니다.")
    private String name;

    private String username;

    @Pattern(regexp = "^.+@.+$", message = "이메일 형식이 맞지 않습니다.")
    private String email;

    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$", message = "비밀번호 형식이 맞지 않습니다.")
    private String password;

    @NotEmpty(message = "주소는 필수입력 값입니다.")
    private String address;

    @NotNull(message = "사용자 성별은 필수입니다.")
    private String sex;

    private String role;

    @Builder
    public MemberJoinRequest(String name, String username, String password, String email, String sex, String address, String role){
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.sex = sex;
        this.address = address;
        this.role = role;
    }
}
