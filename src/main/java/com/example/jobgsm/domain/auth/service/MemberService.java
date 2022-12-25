package com.example.jobgsm.domain.auth.service;

import com.example.jobgsm.domain.auth.presentation.dto.request.MemberSignInRequestDto;
import com.example.jobgsm.domain.auth.presentation.dto.request.MemberSignUpRequestDto;
import com.example.jobgsm.domain.auth.presentation.dto.response.MemberSignInResponseDto;


public interface MemberService {
    void signUp (MemberSignUpRequestDto requestDto);

    MemberSignInResponseDto login(MemberSignInRequestDto requestDto);

    void execute(String accessToken);



}