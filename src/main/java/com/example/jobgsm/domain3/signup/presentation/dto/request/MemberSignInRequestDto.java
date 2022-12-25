package com.example.jobgsm.domain3.signup.presentation.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberSignInRequestDto {


    @NotBlank
    private String email;

    @NotBlank
    private String password;

}
