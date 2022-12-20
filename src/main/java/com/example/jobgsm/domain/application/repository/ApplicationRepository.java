package com.example.jobgsm.domain.application.repository;

import com.example.jobgsm.domain.application.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
}
