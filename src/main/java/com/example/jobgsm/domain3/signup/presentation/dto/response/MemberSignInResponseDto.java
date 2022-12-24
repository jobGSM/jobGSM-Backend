package com.example.jobgsm.domain3.signup.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberSignInResponseDto {
    private String accessToken;
    private String refreshToken;
    private Integer userId;
    private String name;
}
