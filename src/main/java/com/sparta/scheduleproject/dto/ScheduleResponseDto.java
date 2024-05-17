package com.sparta.scheduleproject.dto;

import com.sparta.scheduleproject.entity.Schedule;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class ScheduleResponseDto {
    private Long id;
    private String title;
    private String contents;
    //private String password;
    private String manager;
    private LocalDate createAt;

    public ScheduleResponseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.title = schedule.getTitle();
        this.contents = schedule.getContents();
        this.manager = schedule.getManager();
        //this.password = schedule.getPassword();
        this.createAt = schedule.getCreatedAt();
    }
}
