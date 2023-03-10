package com.example.jobgsm.domain.auth.presentation;


import com.example.jobgsm.domain.auth.presentation.dto.request.UserSignInRequestDto;
import com.example.jobgsm.domain.auth.presentation.dto.request.UserSignUpRequestDto;
import com.example.jobgsm.domain.auth.presentation.dto.response.NewTokenResponse;
import com.example.jobgsm.domain.auth.presentation.dto.response.UserSignInResponseDto;
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
public class AuthController {
    private final MemberServiceImpl memberService;


    @CrossOrigin
    @PostMapping("/signup")
    public void signup(@RequestBody @Validated UserSignUpRequestDto signUpDto){
         memberService.signUp(signUpDto);
    }

    @CrossOrigin
    @PostMapping("/login")
    public UserSignInResponseDto login(@RequestBody @Validated UserSignInRequestDto signInDto) {
        return memberService.login(signInDto);
    }
    @DeleteMapping
    public ResponseEntity<Void> logout(@RequestHeader("Authorization")String accessToken){
        memberService.execute(accessToken);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping
    public ResponseEntity<NewTokenResponse> reIssueToken(@RequestHeader("RefreshToken") String token) {
        NewTokenResponse reIssueToken = memberService.tokenReissuance(token);
        return new ResponseEntity<>(reIssueToken, HttpStatus.OK);
    }
}
