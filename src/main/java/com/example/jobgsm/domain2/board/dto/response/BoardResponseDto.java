package com.example.jobgsm.domain2.board.dto.response;

import com.example.jobgsm.domain2.board.entity.Board;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BoardResponseDto {
    private final Long boardId;
    private final String boardTitle;
    private final String boardContent;
    private final String boardWriter;
    private final String boardStartDate;
    private final String boardEndDate;
    private final int boardApplicant;
    private final LocalDateTime createdDate;
    private final LocalDateTime modifiedDate;

    public BoardResponseDto(Board entity) {
        this.boardId = entity.getBoardId();
        this.boardTitle = entity.getBoardTitle();
        this.boardContent = entity.getBoardContent();
        this.boardWriter = entity.getBoardWriter();
        this.boardStartDate = entity.getBoardStartDate();
        this.boardEndDate = entity.getBoardEndDate();
        this.boardApplicant = entity.getBoardApplicant();
        this.createdDate = entity.getCreatedDate();
        this.modifiedDate = entity.getModifiedDate();
    }


}
