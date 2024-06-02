package com.sparta.scheduleproject.controller;

import com.sparta.scheduleproject.dto.SignupRequestDto;
import com.sparta.scheduleproject.entity.User;
import com.sparta.scheduleproject.responseEntity.UserResponse;
import com.sparta.scheduleproject.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    //회원가입
    @PostMapping("/signup")
    public ResponseEntity<UserResponse> signup(@Valid @RequestBody SignupRequestDto requestDto, BindingResult bindingResult) {
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        
        if(bindingResult.hasErrors()){
            bindingResult.getFieldErrors().forEach((fieldError ->
                    System.out.println(fieldError.getField() + "필드: " + fieldError.getDefaultMessage())));

            return ResponseEntity.badRequest().body(UserResponse.builder()
                    .msg("회원가입 실패")
                    .build());
        }

        userService.signup(requestDto);

        return ResponseEntity.ok(UserResponse.builder()
                .msg("회원가입 성공")
                .build());
    }

}
