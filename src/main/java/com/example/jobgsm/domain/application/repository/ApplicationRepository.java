package com.example.jobgsm.domain.application.repository;

import com.example.jobgsm.domain.application.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

    void deleteApplicationByBoardIdAndUserId(Long boardId, Long userId);
    List<Application> findApplicationsByBoardId(Long boardId);
    List<Application> findApplicationsByUserId(Long userId);
}
