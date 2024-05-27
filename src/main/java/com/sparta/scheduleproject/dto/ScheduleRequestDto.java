package com.sparta.scheduleproject.dto;


import lombok.Getter;

@Getter
public class ScheduleRequestDto {
    private final String title;
    private final String contents;
    private final String manager;
    private final String password;

    public ScheduleRequestDto(String title, String contents, String manager, String password) {
        this.title = title;
        this.contents = contents;
        this.manager = manager;
        this.password = password;
    }
}
