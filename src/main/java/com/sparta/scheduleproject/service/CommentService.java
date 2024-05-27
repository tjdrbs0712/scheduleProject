package com.sparta.scheduleproject.service;

import com.sparta.scheduleproject.dto.CommentRequestDto;
import com.sparta.scheduleproject.dto.CommentResponseDto;
import com.sparta.scheduleproject.entity.Comment;
import com.sparta.scheduleproject.entity.Schedule;
import com.sparta.scheduleproject.entity.User;
import com.sparta.scheduleproject.repository.CommentRepository;
import com.sparta.scheduleproject.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final ScheduleRepository scheduleRepository;

    // 댓글 작성
    @Transactional
    public CommentResponseDto createComment(Long scheduleId, CommentRequestDto commentRequestDto, User user) {

        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new IllegalArgumentException("해당 일정이 존재하지 않습니다."));

        Comment comment = commentRepository.save(new Comment(commentRequestDto, user, schedule));

        return new CommentResponseDto(comment);
    }
}
