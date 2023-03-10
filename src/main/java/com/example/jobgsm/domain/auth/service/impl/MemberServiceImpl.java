package com.example.jobgsm.domain.auth.service.impl;

import com.example.jobgsm.domain.auth.entity.BlackList;
import com.example.jobgsm.domain.auth.entity.RefreshToken;
import com.example.jobgsm.domain.auth.exception.*;
import com.example.jobgsm.domain.auth.presentation.dto.request.UserSignInRequestDto;
import com.example.jobgsm.domain.auth.presentation.dto.request.UserSignUpRequestDto;
import com.example.jobgsm.domain.auth.presentation.dto.response.NewTokenResponse;
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
import com.example.jobgsm.global.exception.exceptionCollection.TokenNotValidException;
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
            throw new EmailAlreadyExistException("???????????? ?????? DB??? ?????? ?????????.");
        }
        EmailAuth emailAuth = emailAuthRepository.findById(signUpDto.getEmail())
                .orElseThrow(() -> new NotVerifyEmailException("???????????? ???????????? ???????????????."));
        if(!emailAuth.getAuthentication()) {
            throw new NotVerifyEmailException("???????????? ???????????? ???????????????");
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
        User user = userRepository.findUserByEmail(signInDto.getEmail()).orElseThrow(() -> new UserNotFoundException("????????? ?????? ???????????????."));
               if(!passwordEncoder.matches(signInDto.getPassword(), user.getPassword())) {
            throw new PasswordWrongException("??????????????? ???????????? ????????????.");
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
        System.out.println(refreshTokenRepository.findAll());
        RefreshToken refreshToken = refreshTokenRepository.findById(user.getEmail()).orElseThrow(()->new RefreshTokenNotFoundException("refreshToken ??? ?????? ??? ????????????."));
        refreshTokenRepository.delete(refreshToken);
        saveBlackList(user.getEmail(),accessToken);
        System.out.println(blackListRepository.findAll());
    }
    private void saveBlackList(String email, String accessToken){
        if(redisTemplate.opsForValue().get(accessToken)!=null){
            throw new BlackListAlreadyExistException("?????????????????? ?????? ????????????????????????.");
        }
        ZonedDateTime accessTokenExpire = tokenProvider.getExpiredAtToken(accessToken,jwtProperties.getAccessSecret());
        BlackList blackList = BlackList.builder()
                .email(email)
                .accessToken(accessToken)
                .build();
        blackListRepository.save(blackList);

    }
    @Transactional(rollbackFor = Exception.class)
    public NewTokenResponse tokenReissuance(String reqToken) {
        String email = tokenProvider.getUserEmail(reqToken, jwtProperties.getRefreshSecret());
        RefreshToken token = refreshTokenRepository.findById(email)
                .orElseThrow(() -> new RefreshTokenNotFoundException("???????????? ?????? refreshToken ?????????"));

        System.out.println(token.getRefreshToken());
        System.out.println(reqToken);
        if(!token.getRefreshToken().equals(reqToken)) {
            throw new TokenNotValidException("????????? ???????????? ????????????");
        }
        String accessToken = tokenProvider.generatedAccessToken(email);
        String refreshToken = tokenProvider.generatedRefreshToken(email);
        token.exchangeRefreshToken(refreshToken);
        refreshTokenRepository.save(token);
        return NewTokenResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

}