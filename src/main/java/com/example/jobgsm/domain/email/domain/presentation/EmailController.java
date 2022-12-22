package com.example.jobgsm.domain.email.domain.presentation;

import com.example.jobgsm.domain.email.domain.presentation.dto.request.EmailSentDto;
import com.example.jobgsm.domain.email.domain.service.EmailCheckService;
import com.example.jobgsm.domain.email.domain.service.EmailSendService;
import lombok.RequiredArgsConstructor;
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

    @PostMapping
    public ResponseEntity<Void> authEmail(@RequestBody @Valid EmailSentDto emailSentDto) {
        emailSendService.execute(emailSentDto);
        return ResponseEntity.ok().build();
    }
    @RequestMapping(method = RequestMethod.HEAD)
    public ResponseEntity<Void> mailVerify(@Email @RequestParam String email, @RequestParam String authKey){
        emailCheckService.execute(email,authKey);
        return ResponseEntity.ok().build();
    }
}
