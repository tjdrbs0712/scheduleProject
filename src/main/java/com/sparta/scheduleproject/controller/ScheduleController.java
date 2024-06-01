package com.sparta.scheduleproject.controller;

import com.sparta.scheduleproject.dto.ScheduleRequestDto;
import com.sparta.scheduleproject.dto.ScheduleResponseDto;
import com.sparta.scheduleproject.responseEntity.ScheduleResponse;
import com.sparta.scheduleproject.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ScheduleResponse> getSchedules(){
        return ResponseEntity.ok().body(ScheduleResponse.builder()
                        .msg("작성일 기준 전체 조회")
                        .data(scheduleService.getSchedules())
                        .build());
    }

    //선택한 일정의 정보 조회
    @GetMapping("/schedules/{id}")
    public ResponseEntity<ScheduleResponse> getSchedulesId(@PathVariable Long id){
        return ResponseEntity.ok().body(ScheduleResponse.builder()
                .msg("선택한 일정 조회")
                .data(scheduleService.getSchedulesId(id))
                .build());

    }

    //일정 추가
    @PostMapping("/schedules")
    public ResponseEntity<ScheduleResponse> createSchedule(@RequestBody ScheduleRequestDto requestDto){
        return ResponseEntity.ok().body(ScheduleResponse.builder()
                .msg("일정 등록")
                .data(scheduleService.createSchedule(requestDto))
                .build());

    }

    //일정 수정
    @PutMapping("/schedules/{id}")
    public ResponseEntity<ScheduleResponse> updateSchedule(@PathVariable Long id, @RequestBody ScheduleRequestDto requestDto){
        return ResponseEntity.ok().body(ScheduleResponse.builder()
                .msg("일정 수정")
                .data(scheduleService.updateSchedule(id, requestDto))
                .build());
    }

    //일정 삭제
    @DeleteMapping("/schedules/{id}/{password}")
    public ResponseEntity<ScheduleResponse> deleteSchedule(@PathVariable Long id, @PathVariable String password){
        scheduleService.deleteSchedule(id, password);
        return ResponseEntity.ok().body(ScheduleResponse.builder()
                .msg("일정 삭제")
                .build());
    }

}
