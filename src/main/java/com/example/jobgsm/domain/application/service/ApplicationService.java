package com.example.jobgsm.domain.application.service;

import com.example.jobgsm.domain.application.dto.request.BoardIdRequest;
import com.example.jobgsm.domain.application.dto.response.ApplicantsResponse;
import com.example.jobgsm.domain.application.dto.response.BoardIdResponse;
import com.example.jobgsm.domain.application.entity.Application;
import com.example.jobgsm.domain.application.exception.BoardNotFoundException;
import com.example.jobgsm.domain.application.repository.ApplicationRepository;
import com.example.jobgsm.domain.user.entity.User;
import com.example.jobgsm.global.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplicationService {
    private final ApplicationRepository applicationRepository;
    private final UserUtil userUtil;

    @Transactional
    public void joinApply(BoardIdRequest applyRequest) {
        User user = userUtil.currentUser();
        Application application = Application.builder()
                .email(user.getEmail())
                .boardId(applyRequest.getBoardId())
                .name(user.getName())
                .grade(user.getGrade())
                .build();
        applicationRepository.save(application);
    }

    @Transactional
    public void joinCancel(BoardIdRequest applyRequestDto) {
        applicationRepository.findByBoardId(applyRequestDto.getBoardId()).orElseThrow(() -> new BoardNotFoundException("게시글을 찾을 수 없습니다"));
        User user = userUtil.currentUser();
        applicationRepository.deleteApplicationByBoardIdAndEmail(applyRequestDto.getBoardId(), user.getEmail());
    }

    @Transactional
    public List<ApplicantsResponse> applicantsList(BoardIdRequest boardIdRequest) {
        //applicationRepository.findByBoardId(boardId).orElseThrow(() -> new BoardNotFoundException("게시글을 찾을 수 없습니다"));
        return applicationRepository.findNameAndGradeByBoardId(boardIdRequest.getBoardId());
    }

    @Transactional
    public List<BoardIdResponse> applicationsList() {
        User user = userUtil.currentUser();
        return applicationRepository.findApplicationsByEmail(user.getEmail());
    }
}
