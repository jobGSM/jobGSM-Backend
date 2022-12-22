package com.example.jobgsm.domain.board.dto.request;


import com.example.jobgsm.domain.board.entity.Board;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardRequestDto {

    private Long boardId;//id
    private String boardTitle;//제목
    private String boardContent;//내용
    private String boardWriter;//작성자
    private String boardDate;//모집 기한
    private int boardApplicant;//모집 인원

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
