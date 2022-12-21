package com.example.jobgsm.domain.application.service;

import com.example.jobgsm.domain.application.dto.request.ApplyRequest;
import com.example.jobgsm.domain.application.dto.request.CancelRequest;
import com.example.jobgsm.domain.application.dto.response.ApplicantsResponse;
import com.example.jobgsm.domain.application.dto.response.ResponseDTO;
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
                .userId(applyRequest.getUserId())
                .name(applyRequest.getName())
                .grade(applyRequest.getGrade())
                .build();
        applicationRepository.save(application);
    }

    @Transactional
    public void joinCancel(CancelRequest cancelRequest) {
        applicationRepository.deleteApplicationByBoardIdAndUserId(cancelRequest.getBoardId(), cancelRequest.getUserId());
    }

    public List<ApplicantsResponse> applicantsList(Long boardId) {
        return applicationRepository.findApplicantsByBoardId(boardId).stream()
                .map(ApplicantsResponse::new)
                .collect(Collectors.toList());
    }

    public List<ResponseDTO> applicationsList(Long userId) {
        return applicationRepository.findApplicationsByUserId(userId).stream()
                .map(ResponseDTO::new)
                .collect(Collectors.toList());
    }
}
