package com.example.jobgsm.domain.signup.presentation;


import com.example.jobgsm.domain.signup.presentation.dto.request.MemberSignInRequestDto;
import com.example.jobgsm.domain.signup.presentation.dto.request.MemberSignUpRequestDto;
import com.example.jobgsm.domain.signup.presentation.dto.response.MemberSignInResponseDto;
import com.example.jobgsm.domain.signup.service.impl.MemberServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;


@RestController
@RequiredArgsConstructor
@Slf4j
public class MemberController {
    private final MemberServiceImpl memberService;


    @CrossOrigin
    @PostMapping("/signup")
    public void signup(@RequestBody @Validated MemberSignUpRequestDto signUpDto){
         memberService.signUp(signUpDto);
    }
    @CrossOrigin
    @PostMapping("/login")
    public MemberSignInResponseDto login(@RequestBody @Validated MemberSignInRequestDto signInDto) {
        return memberService.login(signInDto);
    }
    @PutMapping("/newAccess")
    public MemberSignInResponseDto issueAccessToken(HttpServletRequest request) {
        return memberService.issueAccessToken(request);
    }

    @DeleteMapping
    public ResponseEntity<Void> logout(@RequestHeader("Authorization")String accessToken){
        memberService.execute(accessToken);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
