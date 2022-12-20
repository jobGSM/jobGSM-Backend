package com.example.jobgsm.domain.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CancelRequest {

    @NotNull
    private Long userId;
    @NotNull
    private Long boardId;
}
