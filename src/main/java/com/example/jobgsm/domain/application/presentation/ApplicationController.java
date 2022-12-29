package com.example.jobgsm.domain.application.presentation;

import com.example.jobgsm.domain.application.presentation.dto.request.BoardIdRequest;
import com.example.jobgsm.domain.application.presentation.dto.response.ApplicantsResponse;
import com.example.jobgsm.domain.application.presentation.dto.response.BoardIdResponse;
import com.example.jobgsm.domain.application.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class ApplicationController {

    private final ApplicationService applicationService;

    @PostMapping("/application")
    public ResponseEntity<Void> joinApply(@RequestBody @Valid BoardIdRequest boardId) {
        applicationService.joinApply(boardId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/application")
    public ResponseEntity<Void> joinCancel(@RequestBody @Valid BoardIdRequest boardId) {
        applicationService.joinCancel(boardId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/application")
    public List<ApplicantsResponse> applicantsList(@RequestBody @Valid BoardIdRequest boardId) {
        return applicationService.applicantsList(boardId);
    }

    @GetMapping("/applications")
    public List<BoardIdResponse> applicationsList() {
        return applicationService.applicationsList();
    }
}
