package com.example.jobgsm.domain.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationRequest {

    private Long boardId;
    private Long id;
    private String userName;
    private Integer userGrade;
}
