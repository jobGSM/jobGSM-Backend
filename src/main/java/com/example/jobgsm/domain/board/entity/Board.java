package com.example.jobgsm.domain.board.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardId; // PK

    private String boardTitle; // 제목

    private String boardContent; // 내용

    private String boardWriter; // 작성자

    private int boardApplicant; // 모집 인원

    private String boardDate;//모집 기한


    private LocalDateTime createdDate = LocalDateTime.now(); // 생성일
    private LocalDateTime modifiedDate; // 수정일

    @Builder
    public Board(Long boardId, String boardTitle, String boardContent, String boardWriter, int boardApplicant, String boardDate) {
        this.boardId = boardId;
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
        this.boardWriter = boardWriter;
        this.boardApplicant = boardApplicant;
        this.boardDate = boardDate;
    }

    public void update(String boardTitle,String boardContent,String boardWriter,int boardApplicant,String boardDate){
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
        this.boardWriter = boardWriter;
        this.boardDate = boardDate;
        this.boardApplicant = boardApplicant;
        this.modifiedDate = LocalDateTime.now();
    }
}