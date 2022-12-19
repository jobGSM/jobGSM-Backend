package com.example.jobgsm.domain.user.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    private String userId;
    private String userPassword;
    private String userName;
    private Integer userGrade;
}
