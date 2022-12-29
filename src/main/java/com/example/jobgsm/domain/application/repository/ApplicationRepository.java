package com.example.jobgsm.domain.application.repository;

import com.example.jobgsm.domain.application.dto.response.ApplicantsResponse;
import com.example.jobgsm.domain.application.dto.response.BoardIdResponse;
import com.example.jobgsm.domain.application.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

    void deleteApplicationByBoardIdAndEmail(Long boardId, String email);
    Optional<Application> findByBoardId(Long boardId);
    List<ApplicantsResponse> findNameAndGradeByBoardId(Long boardId);
    List<BoardIdResponse> findApplicationsByEmail(String email);
}
