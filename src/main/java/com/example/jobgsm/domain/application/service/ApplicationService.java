package com.example.jobgsm.domain.application.service;

import com.example.jobgsm.domain.application.dto.request.ApplyRequest;
import com.example.jobgsm.domain.application.dto.request.CancelRequest;
import com.example.jobgsm.domain.application.dto.response.ApplicantsResponse;
import com.example.jobgsm.domain.application.dto.response.BoardIdResponse;
import com.example.jobgsm.domain.application.entity.Application;
import com.example.jobgsm.domain.application.exception.BoardNotFoundException;
import com.example.jobgsm.domain.application.repository.ApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplicationService {
    private final ApplicationRepository applicationRepository;

    @Transactional
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
        applicationRepository.findByBoardId(cancelRequest.getBoardId()).orElseThrow(() -> new BoardNotFoundException("게시글을 찾을 수 없습니다"));
        applicationRepository.deleteApplicationByBoardIdAndUserId(cancelRequest.getBoardId(), cancelRequest.getUserId());
    }

    @Transactional
    public List<ApplicantsResponse> applicantsList(Long boardId) {
        //applicationRepository.findByBoardId(boardId).orElseThrow(() -> new BoardNotFoundException("게시글을 찾을 수 없습니다"));
        return applicationRepository.findNameAndGradeByBoardId(boardId);
    }

    @Transactional
    public List<BoardIdResponse> applicationsList(Long userId) {
        return applicationRepository.findApplicationsByUserId(userId);
    }
}
