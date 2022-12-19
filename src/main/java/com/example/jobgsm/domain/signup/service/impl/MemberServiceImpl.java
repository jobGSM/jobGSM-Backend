package com.example.jobgsm.domain.signup.service.impl;

import com.example.jobgsm.domain.signup.dto.request.MemberSignUpRequestDto;
import com.example.jobgsm.domain.signup.entity.Member;
import com.example.jobgsm.domain.signup.exception.MemberExistException;
import com.example.jobgsm.domain.signup.repository.MemberRepository;
import com.example.jobgsm.domain.signup.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;


    @Transactional
    @Override
    public Integer signUp(MemberSignUpRequestDto signUpDto) {
        if (memberRepository.findByLoginId(signUpDto.getLoginId()).isPresent()) {
            throw new MemberExistException("이미 존재하는 id 입니다.");
        }

        Member member = memberRepository.save(signUpDto.toEntity());
        member.addUserAuthority();
        member.passwordEncode(passwordEncoder);

        return member.getMemberId();
    }
}