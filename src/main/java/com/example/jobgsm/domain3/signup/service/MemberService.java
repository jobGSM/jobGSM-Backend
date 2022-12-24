package com.example.jobgsm.domain3.signup.service;

import com.example.jobgsm.domain3.signup.presentation.dto.request.MemberSignInRequestDto;
import com.example.jobgsm.domain3.signup.presentation.dto.request.MemberSignUpRequestDto;
import com.example.jobgsm.domain3.signup.presentation.dto.response.MemberSignInResponseDto;

import javax.servlet.http.HttpServletRequest;


public interface MemberService {
    void signUp (MemberSignUpRequestDto requestDto);

    MemberSignInResponseDto login(MemberSignInRequestDto requestDto);

    MemberSignInResponseDto issueAccessToken(HttpServletRequest request);



}