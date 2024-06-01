package com.sparta.scheduleproject.controller;

import com.sparta.scheduleproject.dto.CommentRequestDto;
import com.sparta.scheduleproject.responseEntity.CommentResponse;
import com.sparta.scheduleproject.security.UserDetailsImpl;
import com.sparta.scheduleproject.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comment")
public class CommentController {

    private final CommentService commentService;

    //댓글 작성
    @PostMapping("/create")
    public ResponseEntity<CommentResponse> createComment(@Valid @RequestBody CommentRequestDto commentRequestDto
            , @AuthenticationPrincipal UserDetailsImpl userDetails, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            bindingResult.getFieldErrors().forEach(fieldError ->
                    System.out.println(fieldError.getField() + " 필드 : " + fieldError.getDefaultMessage())
                    );

            return ResponseEntity.badRequest().body(CommentResponse.builder()
                    .msg("댓글 작성 실패")
                    .build());
        }

        return ResponseEntity.ok().body(CommentResponse.builder()
                        .msg("댓글 작성 완료")
                        .data(commentService.createComment(commentRequestDto, userDetails.getUser()))
                        .build());
    }

    //댓글 수정
    @PutMapping("/update/{commentId}")
    public ResponseEntity<CommentResponse> updateComment(@PathVariable Long commentId
                                            , @Valid @RequestBody CommentRequestDto commentRequestDto
                                            , @AuthenticationPrincipal UserDetailsImpl userDetails, BindingResult bindingResult){

        if (bindingResult.hasErrors()) {
            String errorMsg = bindingResult.getFieldErrors().stream()
                    .map(fieldError -> fieldError.getField() + " 필드: " + fieldError.getDefaultMessage())
                    .collect(Collectors.joining(", "));
            System.out.println(errorMsg);

            return ResponseEntity.badRequest().body(CommentResponse.builder()
                    .msg("댓글 수정 실패: " + errorMsg)
                    .build());
        }

        return ResponseEntity.ok().body(CommentResponse.builder()
                .msg("댓글 수정 완료")
                .data(commentService.updateComment(commentId, commentRequestDto, userDetails.getUser()))
                .build());

    }

    //댓글 삭제
    @DeleteMapping("/delete/{commentId}")
    public ResponseEntity<CommentResponse> deleteComment(@PathVariable Long commentId
            , @AuthenticationPrincipal UserDetailsImpl userDetails){
        commentService.deleteComment(commentId, userDetails.getUser());

        return ResponseEntity.ok().body(CommentResponse.builder()
                .msg("댓글 삭제 완료")
                .build());
    }
}
