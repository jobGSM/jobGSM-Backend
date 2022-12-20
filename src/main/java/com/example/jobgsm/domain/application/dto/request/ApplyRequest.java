package com.example.jobgsm.domain.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ApplyRequest {

    private Long id;
    private Long boardId;
    private String userName;
    private Integer userGrade;
}
