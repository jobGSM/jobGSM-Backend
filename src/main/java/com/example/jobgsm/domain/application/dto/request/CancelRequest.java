package com.example.jobgsm.domain.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CancelRequest {

    private Long id;
    private Long boardId;
}
