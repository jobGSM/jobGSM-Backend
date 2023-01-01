package com.example.jobgsm.domain.board.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.persistence.*;
import java.time.LocalDateTime;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@DynamicUpdate
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardId; // PK
    private String boardTitle; // 제목
    private String boardContent; // 내용
    private String boardGrade; //학년
    private String boardWriter; // 작성자
    private int boardApplicant; // 모집 인원
    private String boardStartDate;//모집 기한
    private String boardEndDate;

    private final LocalDateTime createdDate = LocalDateTime.now(); // 생성일
    private LocalDateTime modifiedDate; // 수정일

    @Builder
    public Board(Long boardId, String boardTitle, String boardContent, String boardGrade,String boardWriter, int boardApplicant, String boardStartDate,String boardEndDate) {
        this.boardId = boardId;
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
        this.boardGrade = boardGrade;
        this.boardWriter = boardWriter;
        this.boardApplicant = boardApplicant;
        this.boardStartDate = boardStartDate;
        this.boardEndDate =boardEndDate;
    }

    public void update(String boardTitle,String boardContent,int boardApplicant,String boardStartDate,String boardEndDate){
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
        this.boardStartDate = boardStartDate;
        this.boardEndDate = boardEndDate;
        this.boardApplicant = boardApplicant;
        this.modifiedDate = LocalDateTime.now();
    }
}