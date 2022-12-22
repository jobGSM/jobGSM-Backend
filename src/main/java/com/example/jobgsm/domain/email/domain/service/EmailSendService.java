package com.example.jobgsm.domain.email.domain.service;

import com.example.jobgsm.domain.email.domain.entity.EmailAuth;
import com.example.jobgsm.domain.email.domain.exception.AuthCodeExpiredException;
import com.example.jobgsm.domain.email.domain.exception.ManyRequestEmailAuthException;
import com.example.jobgsm.domain.email.domain.presentation.dto.request.EmailSentDto;
import com.example.jobgsm.domain.email.domain.repository.EmailAuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;
import java.util.Random;

@Service
@RequiredArgsConstructor

public class EmailSendService {

    private final EmailAuthRepository emailAuthRepository;
    private final JavaMailSender mailSender;

    @Async
    @Transactional()
    public void sendEmail(EmailSentDto emailSentDto){

        Random random = new Random();
        String authKey = String.valueOf(random.nextInt(8888) + 1111);

        sendAuthEmail(emailSentDto.getEmail(),authKey);
    }

    private void sendAuthEmail(String email, String authKey) {
        String subject = "잡쥐 회원가입 이메일 인증번호";
        String text = "잡쥐 회원가입 인증번호는 <strong>" + authKey + "<strong /> 입니다. <br />";
        EmailAuth emailAuthEntity = emailAuthRepository.findById(email)
                .orElse(EmailAuth.builder()
                        .authentication(false)
                        .randomValue(authKey)
                        .attemptCount(0)
                        .email(email)
                        .build());

        if (emailAuthEntity.getAttemptCount() >= 10) {
            throw new ManyRequestEmailAuthException("발송 횟수 초과");
        }

        emailAuthEntity.updateRandomValue(authKey);
        emailAuthEntity.increaseAttemptCount();

        emailAuthRepository.save(emailAuthEntity);
        try{
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true,"utf-8");
            helper.setTo(email);
            helper.setSubject(subject);
            helper.setText(text,true);
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new AuthCodeExpiredException("메일 발송에 실패했습니다");
        }
    }

}