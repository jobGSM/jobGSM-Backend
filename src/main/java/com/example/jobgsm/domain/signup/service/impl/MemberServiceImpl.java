package com.example.jobgsm.domain.signup.service.impl;

import com.example.jobgsm.domain.signup.dto.request.MemberSignInRequestDto;
import com.example.jobgsm.domain.signup.dto.request.MemberSignUpRequestDto;
import com.example.jobgsm.domain.signup.dto.response.MemberSignUpResponseDto;
import com.example.jobgsm.domain.signup.dto.response.TokenResponseDto;
import com.example.jobgsm.domain.signup.entity.Member;
import com.example.jobgsm.domain.signup.exception.MemberNotFoundException;
import com.example.jobgsm.domain.signup.exception.PasswordNotMatch;
import com.example.jobgsm.domain.signup.repository.MemberRepository;
import com.example.jobgsm.domain.signup.security.jwt.JwtTokenProvider;
import com.example.jobgsm.domain.signup.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;



    @Transactional
    @Override
    public MemberSignUpResponseDto signUp(MemberSignUpRequestDto signUpDto) {

        Member member = memberRepository.save(signUpDto.toEntity());
        member.addUserAuthority();
        member.passwordEncode(passwordEncoder);


        String name = member.getName();
        String grade = member.getGrade();

        return MemberSignUpResponseDto.builder()
                .name(name)
                .grade(grade)
                .build();
    }

    @Transactional
    @Override
    public TokenResponseDto login(MemberSignInRequestDto signInDto) {
        Member member = memberRepository.findByEmail(signInDto.getEmail())
                .orElseThrow(() -> new MemberNotFoundException("존재하지 않은 회원 입니다"));

        validateMatchedPassword(signInDto.getPassword(), member.getPassword());

        String accessToken = jwtTokenProvider.createAccessToken(member.getEmail(), String.valueOf(member.getRole()));
        String refreshToken = jwtTokenProvider.createRefreshToken();

        member.updateRefreshToken(refreshToken);
        memberRepository.save(member);

        return TokenResponseDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    @Transactional
    @Override
    public TokenResponseDto issueAccessToken(HttpServletRequest request) {

        String accessToken = jwtTokenProvider.resolveAccessToken(request);
        String refreshToken = jwtTokenProvider.resolveRefreshToken(request);

        if(jwtTokenProvider.validateAccessToken(accessToken)) {
            log.info("access 토큰 만료됨");
            if(jwtTokenProvider.validateRefreshToken(refreshToken)) {
                log.info("refresh Token 은 유효합니다.");

                Member member = memberRepository.findByEmail(jwtTokenProvider.getUserLoginId(refreshToken))
                        .orElseThrow(() -> new MemberNotFoundException("존재하지 않는 Id 입니다."));

                if(refreshToken.equals(member.getRefreshToken())) {
                    accessToken = jwtTokenProvider.createAccessToken(member.getEmail(), member.getRole().name());
                }
                else {
                    log.info("토큰이 변조되었습니다.");
                }
            }
            else {
                log.info("Refresh Token 이 유효하지 않습니다.");
            }
        }
        return TokenResponseDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
    private void validateMatchedPassword(String validPassword, String memberPassword) {
        if (!passwordEncoder.matches(validPassword, memberPassword)) {
            throw new PasswordNotMatch("비밀번호가 일치하지 않습니다");
        }
    }





}