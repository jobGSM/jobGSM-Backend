package com.example.jobgsm.domain.user.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MyPageResponse {

    private Long userId;
    private String email;
    private String password;
    private String name;
    private String grade;
}
