package com.sparta.scheduleproject;

import com.sparta.scheduleproject.entity.Schedule;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.sparta.scheduleproject.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class InsertTest {
    @PersistenceContext
    EntityManager em;

    @Autowired
    ScheduleRepository scheduleRepository;

    @Test
    @Transactional
    @Rollback(value = false)
    @DisplayName("일정 추가")
    void test1(){
        Schedule schedule = new Schedule();
        schedule.setTitle("2주차 강의");
        schedule.setContents("완강하기");
        schedule.setManager("박성균");
        schedule.setPassword("1234");
        em.persist(schedule);
    }


}
