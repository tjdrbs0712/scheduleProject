package com.sparta.scheduleproject.dto;


import lombok.Getter;

@Getter
public class ScheduleRequesDto {
    private Long id;
    private String title;
    private String contents;
    private String manager;
    private String password;

    public ScheduleRequesDto(String title, String contents, String manager, String password) {
        this.title = title;
        this.contents = contents;
        this.manager = manager;
        this.password = password;
    }
}
