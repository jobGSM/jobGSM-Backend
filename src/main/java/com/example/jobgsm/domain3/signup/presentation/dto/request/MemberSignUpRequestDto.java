package com.example.jobgsm.domain3.signup.presentation.dto.request;


import com.example.jobgsm.domain3.signup.entity.Member;
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
    private String password;

    @NotBlank
    private String name;

    @NotBlank
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
