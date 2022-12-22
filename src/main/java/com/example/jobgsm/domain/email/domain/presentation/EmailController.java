package com.example.jobgsm.domain.email.domain.presentation;

import com.example.jobgsm.domain.email.domain.presentation.dto.request.EmailSentDto;
import com.example.jobgsm.domain.email.domain.service.EmailCheckService;
import com.example.jobgsm.domain.email.domain.service.EmailSendService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Email;

@RestController
@RequiredArgsConstructor
@RequestMapping("/email")
public class EmailController {


    private final EmailSendService emailSendService;
    private final EmailCheckService emailCheckService;

    @PostMapping(value = "/send" , consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> sendEmail(@Valid @RequestBody EmailSentDto emailSentDto) {
        emailSendService.sendEmail(emailSentDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @RequestMapping(method = RequestMethod.HEAD)
    public ResponseEntity<Void> mailCheck(@Email @RequestParam String email, @RequestParam String authKey) {
        emailCheckService.checkEmail(email, authKey);
        return ResponseEntity.ok().build();
    }
}