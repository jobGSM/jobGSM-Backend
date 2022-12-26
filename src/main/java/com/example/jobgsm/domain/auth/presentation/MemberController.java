package com.example.jobgsm.domain.auth.presentation;


import com.example.jobgsm.domain.auth.presentation.dto.request.MemberSignInRequestDto;
import com.example.jobgsm.domain.auth.presentation.dto.request.MemberSignUpRequestDto;
import com.example.jobgsm.domain.auth.presentation.dto.response.MemberSignInResponseDto;
import com.example.jobgsm.domain.auth.service.impl.MemberServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


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
    @CrossOrigin
    @DeleteMapping("/logout")
    public ResponseEntity<Void> logout(@RequestHeader("Authorization")String accessToken){
        memberService.execute(accessToken);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
