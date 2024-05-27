package com.sparta.scheduleproject.controller;

import com.sparta.scheduleproject.dto.SignupRequestDto;
import com.sparta.scheduleproject.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
    public String signup(@Valid @RequestBody SignupRequestDto requestDto, BindingResult bindingResult) {
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        if(!fieldErrors.isEmpty()) {
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                System.out.println(fieldError.getField() + " 필드 : " + fieldError.getDefaultMessage());
            }
            return "회원가입 실패";
        }

        userService.signup(requestDto);

        return "회원가입 완료";
    }
}
