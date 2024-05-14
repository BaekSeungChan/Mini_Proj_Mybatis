package com.example.minproj2_mybatis.member.dto.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

@Getter
public class MemberJoinRequest {

    @NotBlank(message = "사용자 아이디는 필수입니다.")
    private String name;

    @Pattern(regexp = "^.+@.+$", message = "이메일 형식이 맞지 않습니다.")
    private String email;

    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$", message = "비밀번호 형식이 맞지 않습니다.")
    private String password;

    @NotNull(message = "사용자 성별은 필수입니다.")
    private String sex;

    @NotEmpty(message = "주소는 필수입력 값입니다.")
    private String address;

    @Builder
    private MemberJoinRequest(String name, String password, String email, String sex, String address){
        this.name = name;
        this.password = password;
        this.email = email;
        this.sex = sex;
        this.address = address;
    }

}
