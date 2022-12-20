package com.example.jobgsm.domain.application.controller;

import com.example.jobgsm.domain.application.dto.request.ApplyRequest;
import com.example.jobgsm.domain.application.dto.request.CancelRequest;
import com.example.jobgsm.domain.application.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;

@RestController
@RequiredArgsConstructor
public class ApplicationController {

    private final ApplicationService applicationService;

    @PostMapping("/board/application")
    public ResponseEntity<Void> joinApply(@RequestBody ApplyRequest applicationRequest) {
        applicationService.joinApply(applicationRequest);
        return ResponseEntity.ok().build();
    }

    @Transactional
    @DeleteMapping("/board/application")
    public ResponseEntity<Void> joinCancel(@RequestBody CancelRequest cancelRequest) {
        applicationService.joinCancel(cancelRequest);
        return ResponseEntity.ok().build();
    }
}
