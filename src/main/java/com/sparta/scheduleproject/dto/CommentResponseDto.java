package com.sparta.scheduleproject.dto;

import com.sparta.scheduleproject.entity.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class CommentResponseDto {
    private Long id;
    private String content;
    private String username;
    private Long scheduleId;
    private LocalDate createdAt;

    public CommentResponseDto(Comment comment) {
        this.id = comment.getId();
        this.content = comment.getContent();
        this.username = comment.getUser().getUsername();
        this.scheduleId = comment.getSchedule().getId();
        this.createdAt = comment.getCreatedAt();
    }
}


