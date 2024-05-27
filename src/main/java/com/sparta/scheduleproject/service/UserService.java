package com.sparta.scheduleproject.service;

import com.sparta.scheduleproject.dto.SignupRequestDto;
import com.sparta.scheduleproject.entity.User;
import com.sparta.scheduleproject.entity.UserRoleEnum;
import com.sparta.scheduleproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final String ADMIN_TOKEN = "AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC";

    private final UserRepository userRepository;
    //private final PasswordEncoder passwordEncoder;


    //회원가입
    public void signup(SignupRequestDto requestDto) {

        String username = requestDto.getUsername();
        String password = requestDto.getPassword();
        //String password = passwordEncoder.encode(requestDto.getPassword());

        Optional<User> userCheck = userRepository.findByUsername(requestDto.getUsername());

        if (userCheck.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자가 존재합니다.");
        }

        UserRoleEnum role = UserRoleEnum.USER;
        if(requestDto.isAdmin()) {
            if(!ADMIN_TOKEN.equals(requestDto.getAdminToken())){
                throw new IllegalArgumentException("관리자 암호가 틀려 등록이 불가능합니다.");
            }
            role = UserRoleEnum.ADMIN;
        }

        User user = new User(username, password, role);
        userRepository.save(user);
    }
}
