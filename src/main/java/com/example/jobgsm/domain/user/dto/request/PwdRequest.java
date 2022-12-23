package com.example.jobgsm.domain.user.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PwdRequest {

    @NotNull
    private Long userId;
    @NotEmpty
    private String password;
}
