package com.sparta.scheduleproject.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CommentRequestDto {

    @NotNull(message = "일정 id를 입력해주세요")
    private Long scheduleId;

    @NotBlank(message = "내용을 입력해주세요")
    private String content;
}
