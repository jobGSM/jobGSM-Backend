package com.example.jobgsm.domain.application.presentation.dto.response;

import com.example.jobgsm.domain.application.entity.Application;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardIdResponse {

    private Long boardId;

    public BoardIdResponse(Application application) {
        this.boardId = application.getBoardId();
    }
}
