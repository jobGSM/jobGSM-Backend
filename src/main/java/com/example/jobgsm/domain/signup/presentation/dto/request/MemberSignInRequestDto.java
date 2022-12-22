package com.example.jobgsm.domain.signup.presentation.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberSignInRequestDto {

    @NotBlank
    @NotBlank(message = "email은  필수 입력값입니다.")
    private String email;

    @NotBlank
    private String password;


}
