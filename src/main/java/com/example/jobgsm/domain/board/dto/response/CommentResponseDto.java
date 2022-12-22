package com.example.jobgsm.domain.board.dto.response;

import com.example.jobgsm.domain.board.entity.Comment;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentResponseDto {

    private Long id;
    private String commentContents;
    private String commentWriter;
    private Long boardId;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;



    public CommentResponseDto(Comment comment) {
        this.id = comment.getCommentId();
        this.commentContents = comment.getCommentContents();
        this.commentWriter = comment.getCommentWriter();
        this.boardId = comment.getBoardId();
        this.createdDate = comment.getCreatedDate();
        this.modifiedDate = comment.getModifiedDate();
    }
}