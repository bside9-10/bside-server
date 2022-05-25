package com.bside.study.goal.entity;

import com.bside.study.common.entity.LocalDateTimeEntity;
import com.bside.study.goal.dto.SaveGoalDetailRequestDto;
import com.bside.study.user.entity.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class GoalDetail extends LocalDateTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "goal_detail_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "goal_id")
    private Goal goal;

    private String title;

    private LocalDate startDate;

    private LocalDate endDate;

    private LocalTime startTime;

    private LocalTime endTime;

    private boolean notification;

    @Enumerated(EnumType.STRING)
    private GoalDateStatus goalDateStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "goalDetail")
    private List<GoalCalendar> goalCalendars;

    public void updateGoalDetail(SaveGoalDetailRequestDto requestDto) {
        this.startDate = requestDto.getStartDate();
        this.endDate = requestDto.getEndDate();
        this.startTime = LocalTime.of(Integer.parseInt(requestDto.getStartTime().substring(0, 2)), Integer.parseInt(requestDto.getStartTime().substring(2, 4)));
        this.endTime = LocalTime.of(Integer.parseInt(requestDto.getEndTime().substring(0, 2)), Integer.parseInt(requestDto.getEndTime().substring(2, 4)));
        this.notification = requestDto.isNotification();
        this.goalDateStatus = requestDto.getGoalDateStatus();
    }
}
