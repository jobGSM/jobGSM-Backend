package com.example.jobgsm.domain.application.service;

import com.example.jobgsm.domain.application.exception.AlreadyApplicationException;
import com.example.jobgsm.domain.application.exception.FullUpException;
import com.example.jobgsm.domain.application.presentation.dto.request.BoardIdRequest;
import com.example.jobgsm.domain.application.presentation.dto.response.ApplicantsResponse;
import com.example.jobgsm.domain.application.presentation.dto.response.BoardIdResponse;
import com.example.jobgsm.domain.application.entity.Application;
import com.example.jobgsm.domain.application.repository.ApplicationRepository;
import com.example.jobgsm.domain.board.exception.BoardNotFoundException;
import com.example.jobgsm.domain.board.repository.BoardRepository;
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
    private final BoardRepository boardRepository;
    private final UserUtil userUtil;

    @Transactional
    public void joinApply(BoardIdRequest applyRequest) {
        boardRepository.findById(applyRequest.getBoardId()).orElseThrow(() -> new BoardNotFoundException("존재하지 않는 게시글입니다."));
        if(boardRepository.findById(applyRequest.getBoardId()).get().getBoardApplicant() == applicationRepository.findAllByBoardId(applyRequest.getBoardId()).size()){
            throw new FullUpException("신청자리가 꽉 찼습니다.");
        }
        User user = userUtil.currentUser();
        if (applicationRepository.findApplicationByEmailAndBoardId(user.getEmail(), applyRequest.getBoardId()).isPresent()) {
            throw new AlreadyApplicationException("이미 신청한 유저입니다.");
        }
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
    public List<ApplicantsResponse> applicantsList(Long boardId) {
        //applicationRepository.findByBoardId(boardId).orElseThrow(() -> new BoardNotFoundException("게시글을 찾을 수 없습니다"));
        return applicationRepository.findNameAndGradeByBoardId(boardId);
    }

    @Transactional
    public List<BoardIdResponse> applicationsList() {
        User user = userUtil.currentUser();
        return applicationRepository.findApplicationsByEmail(user.getEmail());
    }
}
