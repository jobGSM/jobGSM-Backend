package com.example.jobgsm.domain.user.controller;

import com.example.jobgsm.domain.user.dto.request.IdRequest;
import com.example.jobgsm.domain.user.dto.request.PwdRequest;
import com.example.jobgsm.domain.user.dto.response.MyPageResponse;
import com.example.jobgsm.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @ResponseBody
    @GetMapping
    public ResponseEntity<MyPageResponse> myPage(@RequestBody IdRequest idRequest) {
        MyPageResponse myPageResponse = userService.myPage(idRequest.getId());
        return ResponseEntity.ok().body(myPageResponse);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteUser(@RequestBody IdRequest idRequest) {
        userService.deleteUser(idRequest.getId());
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Void> editUser(@RequestBody PwdRequest pwdRequest) {
        userService.editPwd(pwdRequest);
        return ResponseEntity.ok().build();
    }
}
