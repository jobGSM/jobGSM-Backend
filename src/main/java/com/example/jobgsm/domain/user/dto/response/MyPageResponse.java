package com.example.jobgsm.domain.user.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MyPageResponse {
    private Long id;
    private String userId;
    private String userPassword;
    private String userName;
    private String userGrade;
}
