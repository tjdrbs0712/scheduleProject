package com.sparta.scheduleproject.entity;


import com.sparta.scheduleproject.dto.ScheduleRequesDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name="schedule")
@NoArgsConstructor
public class Schedule extends Timestamped{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="title", nullable = false)
    private String title;
    @Column(name="contents", nullable = false, length = 500)
    private String contents;
    @Column(name="manager", nullable = false, length = 30)
    private String manager;
    @Column(name="password", nullable = false, length = 30)
    private String password;


    public Schedule(ScheduleRequesDto requesDto){
        this.title = requesDto.getTitle();
        this.contents = requesDto.getContents();
        this.manager = requesDto.getManager();
        this.password = requesDto.getPassword();
    }

}
