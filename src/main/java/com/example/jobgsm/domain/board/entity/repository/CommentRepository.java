package com.example.jobgsm.domain.board.entity.repository;

import com.example.jobgsm.domain.board.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findAllByOrderByCreatedDateDesc();
}
