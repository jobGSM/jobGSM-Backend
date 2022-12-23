package com.example.jobgsm.domain.email.service;
import com.example.jobgsm.domain.email.entity.EmailAuth;
import com.example.jobgsm.domain.email.exception.AuthCodeExpiredException;
import com.example.jobgsm.domain.email.exception.ManyRequestEmailAuthException;
import com.example.jobgsm.domain.email.presentation.dto.request.EmailSentDto;
import com.example.jobgsm.domain.email.repository.EmailAuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Random;

@Service
@EnableAsync
@RequiredArgsConstructor
public class EmailSenderService {

    private final EmailAuthRepository emailAuthRepository;
    private final JavaMailSender mailSender;

    @Async
    @Transactional(rollbackFor = Exception.class)
    public void execute(EmailSentDto emailSentDto){

        Random random = new Random();
        String authKey = String.valueOf(random.nextInt(888888) + 111111);


        sendAuthEmail(emailSentDto.getEmail(),authKey);
    }

    private void sendAuthEmail(String email, String authKey) {
        String subject = "잡쥐 인증번호";
        String text = "잡쥐 회원 가입을 위한 인증번호는 " + authKey + "입니다. <br />";
        EmailAuth emailAuthEntity = emailAuthRepository.findById(email)
                .orElse(EmailAuth.builder()
                        .authentication(false)
                        .randomValue(authKey)
                        .attemptCount(0)
                        .email(email)
                        .build());
        if (emailAuthEntity.getAttemptCount() >= 3) {
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