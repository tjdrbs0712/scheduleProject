package com.sparta.scheduleproject.service;

import com.sparta.scheduleproject.dto.ScheduleRequesDto;
import com.sparta.scheduleproject.dto.ScheduleResponseDto;
import com.sparta.scheduleproject.entity.Schedule;
import com.sparta.scheduleproject.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Autowired
    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    //일정 DB 조회
    public List<ScheduleResponseDto> getSchedules() {
        return scheduleRepository.findAllByOrderByCreatedAtDesc().stream().map(ScheduleResponseDto::new).toList();
    }

    //일정 DB 생성
    public ScheduleResponseDto createSchedule(ScheduleRequesDto requestDto) {
        Schedule schedule = new Schedule(requestDto);
        Schedule saveSchedule = scheduleRepository.save(schedule);
        return new ScheduleResponseDto(saveSchedule);
    }

    public ScheduleResponseDto getSchedulesId(Long id) {
        return scheduleRepository.findById(id)
                        .map(ScheduleResponseDto::new)
                        .orElseThrow(() -> new IllegalArgumentException("해당 ID에 맞는 일정이 없습니다."));
    }

    @Transactional
    public ScheduleResponseDto updateSchedule(Long id, String password, ScheduleRequesDto requestDto) {

        Schedule schedule = findSchedule(id, password);
        schedule.update(requestDto);
        return new ScheduleResponseDto(schedule);
    }

    @Transactional
    public void deleteSchedule(Long id, String password) {
        Schedule schedule = findSchedule(id, password);
        scheduleRepository.delete(schedule);
    }

    private Schedule findSchedule(Long id, String password){
        return scheduleRepository.findByIdAndPassword(id, password)
                .orElseThrow(() -> new IllegalArgumentException("ID 또는 PASSWORD가 틀렸습니다."));
    }
}
