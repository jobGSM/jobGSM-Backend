package com.example.jobgsm.domain.signup.presentation;


import com.example.jobgsm.domain.signup.presentation.dto.request.MemberSignInRequestDto;
import com.example.jobgsm.domain.signup.presentation.dto.request.MemberSignUpRequestDto;
import com.example.jobgsm.domain.signup.presentation.dto.response.MemberSignUpResponseDto;
import com.example.jobgsm.domain.signup.presentation.dto.response.TokenResponseDto;
import com.example.jobgsm.domain.signup.service.impl.MemberServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;


@RestController
@RequiredArgsConstructor
@Slf4j
public class MemberController {
    private final MemberServiceImpl memberService;


    @PostMapping("/signup")
    public MemberSignUpResponseDto signup(@RequestBody @Validated MemberSignUpRequestDto signUpDto){
        return memberService.signUp(signUpDto);
    }

    @PostMapping("/login")
    public TokenResponseDto login(@RequestBody @Validated MemberSignInRequestDto signInDto) {
        return memberService.login(signInDto);
    }

    @PutMapping("/newAccess")
    public TokenResponseDto issueAccessToken(HttpServletRequest request) {
        return memberService.issueAccessToken(request);
    }


}
