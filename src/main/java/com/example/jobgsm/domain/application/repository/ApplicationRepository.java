package com.example.jobgsm.domain.application.repository;

import com.example.jobgsm.domain.application.dto.response.ApplicantsResponse;
import com.example.jobgsm.domain.application.dto.response.BoardIdResponse;
import com.example.jobgsm.domain.application.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

    void deleteApplicationByBoardIdAndUserId(Long boardId, Long userId);
    Optional<Application> findByBoardId(Long boardId);
    List<ApplicantsResponse> findNameAndGradeByBoardId(Long boardId);
    List<BoardIdResponse> findApplicationsByUserId(Long userId);
}
