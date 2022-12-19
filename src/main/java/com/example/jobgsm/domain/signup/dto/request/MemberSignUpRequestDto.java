package com.example.jobgsm.domain.signup.dto.request;


import com.example.jobgsm.domain.signup.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;


@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberSignUpRequestDto {
    @NotBlank
    @Size(min = 3,max=10,message = "아이디는 2자 이상 10자 이하로 입력해주세요.")
    private String loginId;

    @NotBlank
    @Size(min = 6, max = 20, message = "비밀번호는 6자 이상 20자 이하로 입력해주세요.")
    private String password;

    @NotBlank(message = "이름을 입력해주세요")
    private String name;

    @Positive  // 양수만 받는다
    @Max(value = 3, message = "학년은 1~3학년 사이여야 합니다.")
    @Min(value = 1, message = "학년은 1~3학년 사이여야 합니다.")
    @NotBlank(message = "학년을 입력해주세요")
    private Integer grade;

    public Member toEntity(){
        return Member.builder()
                .loginId(loginId)
                .password(password)
                .name(name)
                .grade(grade)
                .build();
    }


}
