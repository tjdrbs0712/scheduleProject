package com.sparta.scheduleproject.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequestDto {

    @NotBlank(message = "사용자 이름은 필수 입력 값입니다.")
    @Size(min = 4, max = 10, message = "사용자 이름은 최소 4자 이상, 10자 이하이어야 합니다.")
    @Pattern(regexp = "^[a-z0-9]+$", message = "사용자 이름은 알파벳 소문자(a~z)와 숫자(0~9)로만 구성되어야 합니다.")
    private String username;

    @NotBlank(message = "패스워드는 필수 입력 값입니다.")
    @Size(min = 8, max = 15, message = "패스워드는 최소 8자 이상, 15자 이하이어야 합니다.")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "패스워드는 알파벳 대소문자(a~z, A~Z)와 숫자(0~9)로만 구성되어야 합니다.")
    private String password;

    private boolean admin = false;
    private String adminToken = "";

}
