package com.example.jobgsm.domain.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ApplyRequest {

    @NotNull
    private Long userId;
    @NotNull
    private Long boardId;
    @NotEmpty
    private String name;
    @NotEmpty
    private String grade;
}
