package com.example.jobgsm.domain.application.controller;

import com.example.jobgsm.domain.application.dto.request.ApplyRequest;
import com.example.jobgsm.domain.application.dto.request.CancelRequest;
import com.example.jobgsm.domain.application.dto.response.ApplicantsResponse;
import com.example.jobgsm.domain.application.dto.response.BoardIdResponse;
import com.example.jobgsm.domain.application.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class ApplicationController {

    private final ApplicationService applicationService;

    @PostMapping("/application")
    public ResponseEntity<Void> joinApply(@RequestBody @Valid ApplyRequest applyRequest) {
        applicationService.joinApply(applyRequest);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/application")
    public ResponseEntity<Void> joinCancel(@RequestBody @Valid CancelRequest cancelRequest) {
        applicationService.joinCancel(cancelRequest);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/applicants/{boardId}")
    public List<ApplicantsResponse> applicantsList(@PathVariable @NotNull Long boardId) {
        return applicationService.applicantsList(boardId);
    }

    @GetMapping("/application/{userId}")
    public List<BoardIdResponse> applicationsList(@PathVariable @NotNull Long userId) {
        return applicationService.applicationsList(userId);
    }
}
