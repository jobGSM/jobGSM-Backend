package com.example.jobgsm.domain.user.service;

import com.example.jobgsm.domain.user.dto.response.MyPageResponse;
import com.example.jobgsm.domain.user.entity.User;
import com.example.jobgsm.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public MyPageResponse myPage(Long id) {
        User user = userRepository.findById(id).get();
        return MyPageResponse.builder()
                .id(user.getId())
                .userId(user.getUserId())
                .userPassword(user.getUserPassword())
                .userName(user.getUserName())
                .userGrade(user.getUserGrade())
                .build();
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
