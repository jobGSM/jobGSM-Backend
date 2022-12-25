package com.example.jobgsm.domain.auth.service.impl;

import com.example.jobgsm.domain.auth.entity.BlackList;
import com.example.jobgsm.domain.auth.entity.RefreshToken;
import com.example.jobgsm.domain.auth.exception.BlackListAlreadyExistException;
import com.example.jobgsm.domain.auth.exception.RefreshTokenNotFoundException;
import com.example.jobgsm.domain.auth.presentation.dto.request.MemberSignInRequestDto;
import com.example.jobgsm.domain.auth.presentation.dto.request.MemberSignUpRequestDto;
import com.example.jobgsm.domain.auth.presentation.dto.response.MemberSignInResponseDto;
import com.example.jobgsm.domain.auth.exception.MemberNotFoundException;
import com.example.jobgsm.domain.auth.exception.PasswordNotMatch;
import com.example.jobgsm.domain.auth.repository.BlackListRepository;
import com.example.jobgsm.domain.auth.repository.RefreshTokenRepository;
import com.example.jobgsm.domain.user.entity.User;
import com.example.jobgsm.domain.user.repository.UserRepository;
import com.example.jobgsm.domain.auth.service.MemberService;
import com.example.jobgsm.global.security.jwt.TokenProvider;
import com.example.jobgsm.global.security.jwt.properties.JwtProperties;
import com.example.jobgsm.global.util.UserUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberServiceImpl implements MemberService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final BlackListRepository blackListRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final JwtProperties jwtProperties;
    private final UserRepository userRepository;
    private final UserUtil userUtil;
    private final RedisTemplate redisTemplate;



    @Transactional
    @Override
    public void signUp(MemberSignUpRequestDto signUpDto) {

        User user = userRepository.save(signUpDto.toEntity());
        user.addUserAuthority();
        user.passwordEncode(passwordEncoder);

    }

    @Transactional
    @Override
    public MemberSignInResponseDto login(MemberSignInRequestDto signInDto) {
        User member = userRepository.findUserByEmail(signInDto.getEmail())
                .orElseThrow(() -> new MemberNotFoundException("존재하지 않은 회원 입니다"));

        validateMatchedPassword(signInDto.getPassword(), member.getPassword());

        String accessToken = tokenProvider.generatedAccessToken(member.getEmail());
        String refreshToken = tokenProvider.generatedRefreshToken(member.getEmail())
;       Long userId = member.getMemberId();
        String name = member.getName();

        member.updateRefreshToken(refreshToken);
        userRepository.save(member);

        return MemberSignInResponseDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .userId(userId)
                .name(name)
                .build();
    }

    private void validateMatchedPassword(String validPassword, String memberPassword) {
        if (!passwordEncoder.matches(validPassword, memberPassword)) {
            throw new PasswordNotMatch("비밀번호가 일치하지 않습니다");
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void execute(String accessToken){
        User user = userUtil.currentUser();
        RefreshToken refreshToken = refreshTokenRepository.findRefreshTokenByEmail(user.getEmail()).orElseThrow(()->new RefreshTokenNotFoundException("리프레시 토큰을 찾을 수 없습니다."));
        refreshTokenRepository.delete(refreshToken);
        saveBlackList(user.getEmail(),accessToken);
    }
    private void saveBlackList(String email, String accessToken){
        if(redisTemplate.opsForValue().get(accessToken)!=null){
            throw new BlackListAlreadyExistException("블랙리스트에 이미 등록되어있습니다.");
        }
        ZonedDateTime accessTokenExpire = tokenProvider.getExpiredAtToken(accessToken,jwtProperties.getAccessSecret());
        BlackList blackList = BlackList.builder()
                .email(email)
                .accessToken(accessToken)
                .timeToLive(accessTokenExpire)
                .build();
        blackListRepository.save(blackList);

    }

}