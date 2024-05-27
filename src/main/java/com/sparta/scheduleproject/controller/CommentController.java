package com.sparta.scheduleproject.controller;

import com.sparta.scheduleproject.dto.CommentRequestDto;
import com.sparta.scheduleproject.dto.CommentResponseDto;
import com.sparta.scheduleproject.service.CommentService;
import com.sparta.scheduleproject.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comment")
public class CommentController {

    private final CommentService commentService;

    //댓글 작성
    @PostMapping("/create/{scheduleId}")
    public CommentResponseDto createComment(@PathVariable Long scheduleId, @RequestBody CommentRequestDto commentRequestDto
            , @AuthenticationPrincipal UserDetailsImpl userDetails){
        return commentService.createComment(scheduleId, commentRequestDto, userDetails.getUser());
    }
}
