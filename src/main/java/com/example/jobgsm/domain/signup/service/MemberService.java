package com.example.jobgsm.domain.signup.service;

import com.example.jobgsm.domain.signup.dto.request.MemberSignUpRequestDto;


public interface MemberService {
    Integer signUp (MemberSignUpRequestDto requestDto);


}