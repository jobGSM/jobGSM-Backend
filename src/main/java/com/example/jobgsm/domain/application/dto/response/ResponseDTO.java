package com.example.jobgsm.domain.application.dto.response;

import com.example.jobgsm.domain.application.entity.Application;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDTO {

    private Long userId;
    private Long boardId;
    private String userName;
    private String userGrade;


    public ResponseDTO(Application application) {
        this.userId = application.getUserId();
        this.boardId = application.getBoardId();
        this.userName = application.getUserName();
        this.userGrade = application.getUserGrade();
    }
}
