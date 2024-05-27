package com.sparta.scheduleproject.entity;


import com.sparta.scheduleproject.dto.ScheduleRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @OneToMany(mappedBy = "schedule", fetch = FetchType.LAZY)
    private List<Comment> commentList = new ArrayList<>();





    public Schedule(ScheduleRequestDto requestDto){
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
        this.manager = requestDto.getManager();
        this.password = requestDto.getPassword();
    }

    public void update(ScheduleRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
        this.manager = requestDto.getManager();
    }
}
