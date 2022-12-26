package com.example.jobgsm.domain.auth.service;

import com.example.jobgsm.domain.auth.presentation.dto.request.UserSignInRequestDto;
import com.example.jobgsm.domain.auth.presentation.dto.request.UserSignUpRequestDto;
import com.example.jobgsm.domain.auth.presentation.dto.response.UserSignInResponseDto;


public interface MemberService {
    void signUp (UserSignUpRequestDto requestDto);

    UserSignInResponseDto login(UserSignInRequestDto requestDto);

    void execute(String accessToken);
    UserSignInResponseDto tokenReissuance(String reqToken);



}