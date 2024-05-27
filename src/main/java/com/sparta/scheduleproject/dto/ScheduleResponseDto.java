package com.sparta.scheduleproject.dto;

import com.sparta.scheduleproject.entity.Schedule;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class ScheduleResponseDto {
    private final Long id;
    private final String title;
    private final String contents;
    private final String manager;
    private final LocalDate createAt;

    public ScheduleResponseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.title = schedule.getTitle();
        this.contents = schedule.getContents();
        this.manager = schedule.getManager();
        this.createAt = schedule.getCreatedAt();
    }
}
