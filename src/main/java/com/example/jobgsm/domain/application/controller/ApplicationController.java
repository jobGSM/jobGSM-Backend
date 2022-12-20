package com.example.jobgsm.domain.application.controller;

import com.example.jobgsm.domain.application.dto.request.ApplyRequest;
import com.example.jobgsm.domain.application.dto.request.CancelRequest;
import com.example.jobgsm.domain.application.dto.response.ResponseDTO;
import com.example.jobgsm.domain.application.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ApplicationController {

    private final ApplicationService applicationService;

    @PostMapping("/board/application")
    public ResponseEntity<Void> joinApply(@RequestBody ApplyRequest applyRequest) {
        applicationService.joinApply(applyRequest);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/board/application")
    public ResponseEntity<Void> joinCancel(@RequestBody CancelRequest cancelRequest) {
        applicationService.joinCancel(cancelRequest);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/board/applications/{boardId}")
    public List<ResponseDTO> applicantsList(@PathVariable Long boardId) {
        return applicationService.applicantsList(boardId);
    }

    @GetMapping("/user/application/{userId}")
    public List<ResponseDTO> applicationsList(@PathVariable Long userId) {
        return applicationService.applicationsList(userId);
    }
}
