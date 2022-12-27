package com.example.jobgsm.domain.auth.presentation.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserSignInResponseDto {
    private String accessToken;
    private String refreshToken;
    //private Long userId;
    private String name;
    private String grade;
}
