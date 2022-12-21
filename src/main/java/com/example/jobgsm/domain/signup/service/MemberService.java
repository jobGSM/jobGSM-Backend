package com.example.jobgsm.domain.signup.service;

import com.example.jobgsm.domain.signup.presentation.dto.request.MemberSignInRequestDto;
import com.example.jobgsm.domain.signup.presentation.dto.request.MemberSignUpRequestDto;
import com.example.jobgsm.domain.signup.presentation.dto.response.MemberSignUpResponseDto;
import com.example.jobgsm.domain.signup.presentation.dto.response.TokenResponseDto;

import javax.servlet.http.HttpServletRequest;


public interface MemberService {
    MemberSignUpResponseDto signUp (MemberSignUpRequestDto requestDto);

    TokenResponseDto login(MemberSignInRequestDto requestDto);

    TokenResponseDto issueAccessToken(HttpServletRequest request);



}