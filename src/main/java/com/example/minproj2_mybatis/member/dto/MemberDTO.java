package com.example.minproj2_mybatis.member.dto;


import com.example.minproj2_mybatis.member.entity.MemberEntity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@ToString
@AllArgsConstructor @NoArgsConstructor
@Builder
public class MemberDTO {

    private Long id;

    @NotBlank(message = "이름은 필수입력 값입니다.")
    private String name;

    @NotEmpty(message = "이메일은 필수입력 값입니다.")
    @Email(message = "이메일 형식으로 입력해주세요.")
    private String email;

    @NotEmpty(message = "비밀번호는 필수입력 값입니다.")
    @Length(min = 4, max = 16, message = "비밀번호는 4자 이상 16자 이하로 입력해주세요")
    private String password;

    private String passwordCheck;

    @NotEmpty(message = "주소는 필수입력 값입니다.")
    private String address;

    private Enum role;

    public static MemberDTO toDTO(MemberEntity member){
        return MemberDTO.builder()
                .id(member.getId())
                .name(member.getName())
                .email(member.getEmail())
                .address(member.getAddress())
                .role(member.getRole())
                .build();
    }

}
