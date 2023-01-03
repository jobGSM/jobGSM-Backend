package com.example.jobgsm.domain.board.repository;

import com.example.jobgsm.domain.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findByBoardTitleContaining(String keyword);
}