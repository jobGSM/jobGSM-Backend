package com.example.jobgsm.global.util;

import com.example.jobgsm.domain.user.entity.User;
import com.example.jobgsm.domain.user.exception.UserNotFoundException;
import com.example.jobgsm.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserUtil {

    private final UserRepository userRepository;

    public User currentUser(){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println(email);
        return userRepository. findUserByEmail(email).orElseThrow(()->new UserNotFoundException("유저를 찾을 수 없습니다."));
    }
}
