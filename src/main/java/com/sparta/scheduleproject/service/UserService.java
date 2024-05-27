package com.sparta.scheduleproject.service;

import com.sparta.scheduleproject.dto.SignupRequestDto;
import com.sparta.scheduleproject.entity.User;
import com.sparta.scheduleproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    //회원가입
    public void signup(SignupRequestDto requestDto) {

        Optional<User> userCheck = userRepository.findByUsername(requestDto.getUsername());

        if (userCheck.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자가 존재합니다.");
        }

        User user = new User(requestDto);
        userRepository.save(user);
    }
}
