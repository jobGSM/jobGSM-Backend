package com.example.jobgsm.domain.email.domain.presentation.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EmailSentDto {
    @Email
    @NotBlank(message = "이메일(필수)")
    private String email;
}