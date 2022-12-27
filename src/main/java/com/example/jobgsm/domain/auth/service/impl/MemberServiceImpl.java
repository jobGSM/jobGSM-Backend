package com.example.jobgsm.domain.auth.service.impl;

import com.example.jobgsm.domain.auth.entity.BlackList;
import com.example.jobgsm.domain.auth.entity.RefreshToken;
import com.example.jobgsm.domain.auth.exception.*;
import com.example.jobgsm.domain.auth.presentation.dto.request.UserSignInRequestDto;
import com.example.jobgsm.domain.auth.presentation.dto.request.UserSignUpRequestDto;
import com.example.jobgsm.domain.auth.presentation.dto.response.UserSignInResponseDto;
import com.example.jobgsm.domain.auth.repository.BlackListRepository;
import com.example.jobgsm.domain.auth.repository.RefreshTokenRepository;
import com.example.jobgsm.domain.email.entity.EmailAuth;
import com.example.jobgsm.domain.email.repository.EmailAuthRepository;
import com.example.jobgsm.domain.user.entity.User;
import com.example.jobgsm.domain.user.exception.PasswordWrongException;
import com.example.jobgsm.domain.user.exception.UserNotFoundException;
import com.example.jobgsm.domain.user.repository.UserRepository;
import com.example.jobgsm.domain.auth.service.MemberService;
import com.example.jobgsm.global.exception.exceptionCollection.TokenNotVaildException;
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
@RequiredArgsConstructor
@Slf4j
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
    public void signUp(UserSignUpRequestDto signUpDto) {

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
                .name(signUpDto.getName())
                .grade(signUpDto.getGrade())
                .build();
        userRepository.save(user);

    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public UserSignInResponseDto login(UserSignInRequestDto signInDto) {
        User user = userRepository.findUserByEmail(signInDto.getEmail()).orElseThrow(() -> new UserNotFoundException("유저를 찾지 못했습니다."));
        if(!passwordEncoder.matches(signInDto.getPassword(), user.getPassword())) {
            throw new PasswordWrongException("비밀번호가 올바르지 않습니다.");
        }
        String accessToken = tokenProvider.generatedAccessToken(signInDto.getEmail());
        String refreshToken = tokenProvider.generatedRefreshToken(signInDto.getEmail());
        RefreshToken entityToRedis = new RefreshToken(signInDto.getEmail(), refreshToken);
        System.out.println("entityToRedis = " + entityToRedis);
        refreshTokenRepository.save(entityToRedis);
        System.out.println(refreshTokenRepository.findAll());
        return UserSignInResponseDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .grade(user.getGrade())
                .name(user.getName())
                .build();
    }


    @Transactional(rollbackFor = Exception.class)
    public void execute(String accessToken){
        User user = userUtil.currentUser();
        System.out.println(user.getEmail());
        RefreshToken refreshToken = refreshTokenRepository.findRefreshTokenByEmail(user.getEmail()).orElseThrow(()->new RefreshTokenNotFoundException("refreshToken 을 찾을 수 없습니다."));
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
    @Transactional(rollbackFor = Exception.class)
    public UserSignInResponseDto tokenReissuance(String reqToken) {
        String email = tokenProvider.getUserEmail(reqToken, jwtProperties.getRefreshSecret());
        RefreshToken token = refreshTokenRepository.findById(email)
                .orElseThrow(() -> new RefreshTokenNotFoundException("존재하지 않은 refreshToken 입니다"));
        if(!token.getRefreshToken().equals(reqToken)) {
            throw new TokenNotVaildException("토큰이 유효하지 않습니다");
        }
        String accessToken = tokenProvider.generatedAccessToken(email);
        String refreshToken = tokenProvider.generatedRefreshToken(email);
        ZonedDateTime expiredAt = tokenProvider.getExpiredAtToken(accessToken, jwtProperties.getAccessSecret());
        token.exchangeRefreshToken(refreshToken);
        refreshTokenRepository.save(token);
        return UserSignInResponseDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

}