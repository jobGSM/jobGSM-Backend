package com.example.jobgsm.domain.auth.service.impl;

import com.example.jobgsm.domain.auth.entity.BlackList;
import com.example.jobgsm.domain.auth.entity.RefreshToken;
import com.example.jobgsm.domain.auth.exception.*;
import com.example.jobgsm.domain.auth.presentation.dto.request.MemberSignInRequestDto;
import com.example.jobgsm.domain.auth.presentation.dto.request.MemberSignUpRequestDto;
import com.example.jobgsm.domain.auth.presentation.dto.response.UserSignInResponseDto;
import com.example.jobgsm.domain.auth.repository.BlackListRepository;
import com.example.jobgsm.domain.auth.repository.RefreshTokenRepository;
import com.example.jobgsm.domain.email.entity.EmailAuth;
import com.example.jobgsm.domain.email.repository.EmailAuthRepository;
import com.example.jobgsm.domain.user.entity.User;
import com.example.jobgsm.domain.user.exception.UserNotFoundException;
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
    private final EmailAuthRepository emailAuthRepository;



    @Transactional
    @Override
    public void signUp(MemberSignUpRequestDto signUpDto) {

        if(userRepository.existsByEmail(signUpDto.getEmail())) {
            throw new EmailAlreadyExistException("이메일이 이미 DB에 존재 합니다.");
        }
        EmailAuth emailAuth = emailAuthRepository.findById(signUpDto.getEmail())
                .orElseThrow(() -> new NotVerifyEmailException("이메일이 인증되지 않았습니다."));
        if(!emailAuth.getAuthentication()) {
            throw new NotVerifyEmailException("이메일이 인증되지 않았습니다");
        }
        User user = User.builder()
                .email(signUpDto.getEmail())
                .password(passwordEncoder.encode(signUpDto.getPassword()))
                .build();
        userRepository.save(user);

    }

    @Transactional
    @Override
    public UserSignInResponseDto login(MemberSignInRequestDto signInDto) {
        User user = userRepository.findUserByEmail(signInDto.getEmail()).orElseThrow(() -> new UserNotFoundException("유저를 찾지 못했습니다."));
        if(!passwordEncoder.matches(signInDto.getPassword(), user.getPassword())) {
            throw new MisMatchPasswordException("비밀번호가 올바르지 않습니다.");
        }
        String accessToken = tokenProvider.generatedAccessToken(signInDto.getEmail());
        String refreshToken = tokenProvider.generatedRefreshToken(signInDto.getEmail());
        RefreshToken entityToRedis = new RefreshToken(signInDto.getEmail(), refreshToken, tokenProvider.getREFRESH_TOKEN_EXPIRE_TIME());
        refreshTokenRepository.save(entityToRedis);
        return UserSignInResponseDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .expiredAt(tokenProvider.getExpiredAtToken(accessToken, jwtProperties.getAccessSecret()))
                .build();
    }

    private void validateMatchedPassword(String validPassword, String memberPassword) {
        if (!passwordEncoder.matches(validPassword, memberPassword)) {
            throw new MisMatchPasswordException("비밀번호가 일치하지 않습니다");
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