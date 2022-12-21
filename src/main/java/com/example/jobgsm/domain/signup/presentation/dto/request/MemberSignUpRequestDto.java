package com.example.jobgsm.domain.signup.presentation.dto.request;


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
    private String email;

    @NotBlank
    @Size(min = 6, max = 20, message = "비밀번호는 6자 이상 20자 이하로 입력해주세요.")
    private String password;

    @NotBlank(message = "이름을 입력해주세요")
    private String name;

    @NotBlank(message = "학년 또는 직급을 선택해주세요")
    private String grade;

    
    public Member toEntity(){
        return Member.builder()
                .email(email)
                .password(password)
                .name(name)
                .grade(grade)
                .build();
    }


}
