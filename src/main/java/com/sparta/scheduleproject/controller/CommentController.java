package com.sparta.scheduleproject.controller;

import com.sparta.scheduleproject.dto.CommentRequestDto;
import com.sparta.scheduleproject.dto.CommentResponseDto;
import com.sparta.scheduleproject.service.CommentService;
import com.sparta.scheduleproject.security.UserDetailsImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comment")
public class CommentController {

    private final CommentService commentService;

    //댓글 작성
    @PostMapping("/create")
    public CommentResponseDto createComment(@Valid @RequestBody CommentRequestDto commentRequestDto
            , @AuthenticationPrincipal UserDetailsImpl userDetails, BindingResult bindingResult){

        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        if(!fieldErrors.isEmpty()) {
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                System.out.println(fieldError.getField() + " 필드 : " + fieldError.getDefaultMessage());
            }
            return null;
        }

        return commentService.createComment(commentRequestDto, userDetails.getUser());
    }
    @PutMapping("/update/{commentId}")
    public CommentResponseDto updateComment(@PathVariable Long commentId
                                            , @Valid @RequestBody CommentRequestDto commentRequestDto
                                            , @AuthenticationPrincipal UserDetailsImpl userDetails){
        return commentService.updateComment(commentId, commentRequestDto, userDetails.getUser());
    }

    @DeleteMapping("/delete/{commentId}")
    public void deleteComment(@PathVariable Long commentId
            , @AuthenticationPrincipal UserDetailsImpl userDetails){
        commentService.deleteComment(commentId, userDetails.getUser());
    }
}
