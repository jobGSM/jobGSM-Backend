package com.example.jobgsm.domain.application.service;

import com.example.jobgsm.domain.application.dto.request.ApplyRequest;
import com.example.jobgsm.domain.application.dto.request.CancelRequest;
import com.example.jobgsm.domain.application.entity.Application;
import com.example.jobgsm.domain.application.repository.ApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApplicationService {
    private final ApplicationRepository applicationRepository;

    public void joinApply(ApplyRequest applicationRequest) {
        Application application = Application.builder()
                .boardId(applicationRequest.getBoardId())
                .id(applicationRequest.getId())
                .userName(applicationRequest.getUserName())
                .userGrade(applicationRequest.getUserGrade())
                .build();
        applicationRepository.save(application);
    }

    public void joinCancel(CancelRequest cancelRequest) {
        applicationRepository.deleteApplicationByBoardIdAndId(cancelRequest.getBoardId(), cancelRequest.getId());
    }
}
