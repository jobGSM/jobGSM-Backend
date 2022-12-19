package com.example.jobgsm.domain.user.controller;

import com.example.jobgsm.domain.user.dto.response.MyPageResponse;
import com.example.jobgsm.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user/{id}")
public class UserController {

    private final UserService userService;

    @ResponseBody
    @GetMapping
    public ResponseEntity<MyPageResponse> myPage(@PathVariable Long id) {
        MyPageResponse myPageResponse = userService.myPage(id);
        return ResponseEntity.ok().body(myPageResponse);
    }
}
