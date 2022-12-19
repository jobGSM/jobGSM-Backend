package com.example.jobgsm.domain.user.service;

import com.example.jobgsm.domain.user.dto.request.PwdRequest;
import com.example.jobgsm.domain.user.dto.response.MyPageResponse;
import com.example.jobgsm.domain.user.entity.User;
import com.example.jobgsm.domain.user.exception.NotNullException;
import com.example.jobgsm.domain.user.exception.UserNotFoundException;
import com.example.jobgsm.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public MyPageResponse myPage(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("유저를 찾을 수 없습니다."));
        return MyPageResponse.builder()
                .id(user.getId())
                .userId(user.getUserId())
                .userPassword(user.getUserPassword())
                .userName(user.getUserName())
                .userGrade(user.getUserGrade())
                .build();
    }

    public void deleteUser(Long id) {
        userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("유저를 찾을 수 없습니다."));
        userRepository.deleteById(id);
    }

    public void editPwd(PwdRequest pwdRequest) {
        if (pwdRequest.getUserPassword() == null) {
            throw new NotNullException("null값은 허용되지 않습니다.");
        }

        User user = userRepository.findById(pwdRequest.getId())
                .orElseThrow(() -> new UserNotFoundException("유저를 찾을 수 없습니다."));

        user.updateUserPassword(pwdRequest.getUserPassword());
    }
}
