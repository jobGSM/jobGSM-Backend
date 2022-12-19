package com.example.jobgsm.domain.board.dto;

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
    private String boardApplicant;
    private LocalDateTime createDate;
    private LocalDateTime modifiedDate;

    public BoardResponseDto(Board entity) {
        this.boardId = boardId;
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
        this.boardWriter = boardWriter;
        this.boardDate = boardDate;
        this.boardApplicant = boardApplicant;
        this.createDate = createDate;
        this.modifiedDate = modifiedDate;
    }


}
