package com.example.jobgsm.domain.user.presentation;

import com.example.jobgsm.domain.user.presentation.dto.request.IdRequest;
import com.example.jobgsm.domain.user.presentation.dto.request.PwdRequest;
import com.example.jobgsm.domain.user.presentation.dto.response.MyPageResponse;
import com.example.jobgsm.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<MyPageResponse> myPage(@RequestBody @Valid IdRequest idRequest) {
        MyPageResponse myPageResponse = userService.myPage(idRequest.getUserId());
        return ResponseEntity.ok().body(myPageResponse);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteUser(@RequestBody @Valid PwdRequest pwdRequest) {
        userService.deleteUser(pwdRequest);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Void> editUser(@RequestBody @Valid PwdRequest pwdRequest) {
        userService.editPwd(pwdRequest);
        return ResponseEntity.ok().build();
    }
}
