package com.example.jobgsm.domain.board.dto.response;

import com.example.jobgsm.domain.board.entity.Board;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BoardResponseDto {
    private Long boardId;
    private String boardTitle;
    private String boardContent;
    private String boardWriter;
    private String boardDate;
    private int boardApplicant;
    private LocalDateTime createDate;
    private LocalDateTime modifiedDate;

    public BoardResponseDto(Board entity) {
        this.boardId = entity.getBoardId();
        this.boardTitle = entity.getBoardTitle();
        this.boardContent = entity.getBoardContent();
        this.boardWriter = entity.getBoardWriter();
        this.boardDate = entity.getBoardDate();
        this.boardApplicant = entity.getBoardApplicant();
        this.createDate = entity.getCreatedDate();
        this.modifiedDate = entity.getModifiedDate();
    }


}
