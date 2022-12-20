package com.example.jobgsm.domain.application.service;

import com.example.jobgsm.domain.application.dto.request.ApplicationRequest;
import com.example.jobgsm.domain.application.entity.Application;
import com.example.jobgsm.domain.application.repository.ApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApplicationService {
    private final ApplicationRepository applicationRepository;

    public void joinApply(ApplicationRequest applicationRequest) {
        Application application = Application.builder()
                .boardId(applicationRequest.getBoardId())
                .id(applicationRequest.getId())
                .userName(applicationRequest.getUserName())
                .userGrade(applicationRequest.getUserGrade())
                .build();
        applicationRepository.save(application);
    }
}
