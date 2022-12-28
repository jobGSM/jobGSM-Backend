package com.example.jobgsm.domain.board.presentation.dto.request;


import com.example.jobgsm.domain.board.entity.Board;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardRequestDto {
    private Long boardId;
    private String boardTitle;
    private String boardContent;
    private String boardWriter;
    private String boardStartDate;
    private String boardEndDate;
    private int boardApplicant;

    public Board toEntity(){
        return Board.builder()
                .boardTitle(boardTitle)
                .boardContent(boardContent)
                .boardWriter(boardWriter)
                .boardStartDate(boardStartDate)
                .boardEndDate(boardEndDate)
                .boardApplicant(boardApplicant)
                .build();
    }
}
