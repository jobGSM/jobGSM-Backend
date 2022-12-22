package com.example.jobgsm.domain.board.entity;


import lombok.*;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name="comment_table")
public class Comment extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long commentId;
    private Long boardId;
    private String commentContents;
    private String commentWriter;

    @Builder
    public Comment(Long commentId, String commentContents, String commentWriter, Long boardId) {
        this.commentId = commentId;
        this.commentContents = commentContents;
        this.commentWriter = commentWriter;
        this.boardId = boardId;
    }




}