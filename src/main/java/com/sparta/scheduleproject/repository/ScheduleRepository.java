package com.sparta.scheduleproject.repository;

import com.sparta.scheduleproject.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    //작성일 기준 내림차순 조회
    List<Schedule> findAllByOrderByCreatedAtDesc();
    //id로 일정 조회
    Optional<Schedule> findByIdAndPassword(Long id, String password);
}
