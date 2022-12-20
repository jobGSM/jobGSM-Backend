package com.example.jobgsm.domain.application.service;

import com.example.jobgsm.domain.application.dto.request.ApplyRequest;
import com.example.jobgsm.domain.application.dto.request.CancelRequest;
import com.example.jobgsm.domain.application.dto.response.ResponseApplicants;
import com.example.jobgsm.domain.application.entity.Application;
import com.example.jobgsm.domain.application.repository.ApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ApplicationService {
    private final ApplicationRepository applicationRepository;

    public void joinApply(ApplyRequest applyRequest) {
        Application application = Application.builder()
                .boardId(applyRequest.getBoardId())
                .id(applyRequest.getId())
                .userName(applyRequest.getUserName())
                .userGrade(applyRequest.getUserGrade())
                .build();
        applicationRepository.save(application);
    }

    @Transactional
    public void joinCancel(CancelRequest cancelRequest) {
        applicationRepository.deleteApplicationByBoardIdAndId(cancelRequest.getBoardId(), cancelRequest.getId());
    }

    public List<ResponseApplicants> applicantsList(Long boardId) {
        return applicationRepository.findApplicantsByBoardId(boardId).stream()
                .map(ResponseApplicants::new)
                .collect(Collectors.toList());
    }
}
