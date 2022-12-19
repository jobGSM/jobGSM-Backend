package com.example.jobgsm.domain.board.dto;


import com.example.jobgsm.domain.board.entity.Board;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardRequestDto {
    private String boardTitle;
    private String boardContent;
    private String boardWriter;
    private String boardDate;
    private int boardApplicant;

    public Board toEntity(){
        return Board.builder()
                .boardTitle(boardTitle)
                .boardContent(boardContent)
                .boardWriter(boardWriter)
                .boardDate(boardDate)
                .boardApplicant(boardApplicant)
                .build();
    }
}
