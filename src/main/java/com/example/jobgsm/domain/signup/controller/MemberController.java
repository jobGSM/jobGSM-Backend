package com.example.jobgsm.domain.signup.controller;


import com.example.jobgsm.domain.signup.dto.request.MemberSignUpRequestDto;
import com.example.jobgsm.domain.signup.service.impl.MemberServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
@Slf4j
public class MemberController {
    private final MemberServiceImpl memberService;

    @PostMapping("/signup")
    public Integer signup(@RequestBody @Validated MemberSignUpRequestDto signUpDto){
        return memberService.signUp(signUpDto);
    }
}
