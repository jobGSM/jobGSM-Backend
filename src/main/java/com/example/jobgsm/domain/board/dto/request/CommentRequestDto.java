package com.example.jobgsm.domain.board.dto.request;

import com.example.jobgsm.domain.board.entity.Comment;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentRequestDto {
    private Long commentId;
    private Long boardId;
    private String commentContents;
    private String commentWriter;
    private LocalDateTime createdDate = LocalDateTime.now();
    private LocalDateTime modifiedDate = LocalDateTime.now();



    public Comment toEntity() {
         return Comment.builder()
                 .commentContents(commentContents)
                 .commentWriter(commentWriter)
                 .boardId(boardId)
                 .build();
    }
}