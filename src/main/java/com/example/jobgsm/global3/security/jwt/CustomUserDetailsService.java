package com.example.jobgsm.global3.security.jwt;


import com.example.jobgsm.domain3.signup.exception.MemberNotFoundException;
import com.example.jobgsm.domain3.signup.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String loginId) throws MemberNotFoundException {
        return memberRepository.findByEmail(loginId)
                .orElseThrow(() -> new MemberNotFoundException("존재하지 않은 Id 입니다"));
    }
}