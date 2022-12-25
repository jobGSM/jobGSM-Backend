package com.example.jobgsm.domain.user.service;

import com.example.jobgsm.domain.user.presentation.dto.request.PwdRequest;
import com.example.jobgsm.domain.user.presentation.dto.response.MyPageResponse;
import com.example.jobgsm.domain.user.entity.User;
import com.example.jobgsm.domain.user.exception.PasswordWrongException;
import com.example.jobgsm.domain.user.exception.UserNotFoundException;
import com.example.jobgsm.domain.user.repository.UserRepository;
import com.example.jobgsm.global.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserUtil userUtil;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public MyPageResponse myPage(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("유저를 찾을 수 없습니다."));
        return MyPageResponse.builder()
                .userId(user.getMemberId())
                .email(user.getEmail())
                .password(user.getPassword())
                .name(user.getName())
                .grade(user.getGrade())
                .build();
    }

//    public void deleteUser(Long id) {
//        userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("유저를 찾을 수 없습니다."));
//        userRepository.deleteById(id);
//    }d

    @Transactional
    public void deleteUser(PwdRequest pwdRequest){
        User user = userUtil.currentUser();
        if(!passwordEncoder.matches(pwdRequest.getPassword(), user.getPassword())) {
            throw new PasswordWrongException("비밀번호가 올바르지 않습니다.");
        }
        userRepository.delete(user);
    }

    @Transactional
    public void editPwd(PwdRequest pwdRequest) {
        User user = userRepository.findById(pwdRequest.getUserId())
                .orElseThrow(() -> new UserNotFoundException("유저를 찾을 수 없습니다."));

        user.updatePassword(pwdRequest.getPassword());
    }
}
