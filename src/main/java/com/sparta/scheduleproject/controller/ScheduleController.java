package com.sparta.scheduleproject.controller;

import com.sparta.scheduleproject.dto.ScheduleRequestDto;
import com.sparta.scheduleproject.dto.ScheduleResponseDto;
import com.sparta.scheduleproject.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ScheduleController {

    private final ScheduleService scheduleService;

    @Autowired
    public ScheduleController(ScheduleService scheduleService){
        this.scheduleService=scheduleService;
    }

    //전체 일정 작성일 기준 내림차운으로 정렬
    @GetMapping("/schedules")
    public List<ScheduleResponseDto> getSchedules(){
        return scheduleService.getSchedules();
    }

    //선택한 일정의 정보 조회
    @GetMapping("/schedules/{id}")
    public ScheduleResponseDto getSchedulesId(@PathVariable Long id){
        return scheduleService.getSchedulesId(id);
    }

    //일정 추가
    @PostMapping("/schedules")
    public ScheduleResponseDto createSchedule(@RequestBody ScheduleRequestDto requestDto){
        return scheduleService.createSchedule(requestDto);
    }

    //일정 수정
    @PutMapping("/schedules/{id}")
    public ScheduleResponseDto updateSchedule(@PathVariable Long id, @RequestBody ScheduleRequestDto requestDto){
        return scheduleService.updateSchedule(id, requestDto);
    }

    //일정 삭제
    @DeleteMapping("/schedules/{id}/{password}")
    public void deleteSchedule(@PathVariable Long id, @PathVariable String password){
        scheduleService.deleteSchedule(id, password);
    }

}
