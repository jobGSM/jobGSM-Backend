package com.example.jobgsm.domain4.email.service;

import com.example.jobgsm.domain4.email.entity.EmailAuth;
import com.example.jobgsm.domain4.email.exception.MemberNotFoundException;
import com.example.jobgsm.domain4.email.exception.MisMatchAuthCodeException;
import com.example.jobgsm.domain4.email.repository.EmailAuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class EmailCheckerService {

    private final EmailAuthRepository emailAuthRepository;

    @Transactional(rollbackFor = Exception.class)
    public void execute(String email,String authKey){
        EmailAuth emailAuthEntity = emailAuthRepository.findById(email).orElseThrow(()->new MemberNotFoundException("유저를 찾을 수 없습니다."));
        checkAuthKey(emailAuthEntity,authKey);
        emailAuthEntity.updateAuthentication(true);
        emailAuthRepository.save(emailAuthEntity);
    }

    private void checkAuthKey(EmailAuth emailAuthEntity, String authKey){
        if(!Objects.equals(emailAuthEntity.getRandomValue(), authKey)){
            throw new MisMatchAuthCodeException("인증번호가 일치하지 않습니다.");
        }
    }
}