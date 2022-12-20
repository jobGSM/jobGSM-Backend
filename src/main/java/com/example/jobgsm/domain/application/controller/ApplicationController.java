package com.example.jobgsm.domain.application.controller;

import com.example.jobgsm.domain.application.dto.request.ApplicationRequest;
import com.example.jobgsm.domain.application.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ApplicationController {

    private final ApplicationService applicationService;

    @PostMapping("/board/application")
    public ResponseEntity<Void> joinApply(@RequestBody ApplicationRequest applicationRequest) {
        applicationService.joinApply(applicationRequest);
        return ResponseEntity.ok().build();
    }
}
