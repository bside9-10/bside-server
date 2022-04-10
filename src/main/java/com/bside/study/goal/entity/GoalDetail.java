package com.bside.study.goal.entity;

import com.bside.study.common.entity.LocalDateTimeEntity;
import com.bside.study.goal.dto.SaveGoalDetailRequestDto;
import com.bside.study.user.entity.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

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

    private String startTime;

    private String endTime;

    private boolean notification;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public void updateGoalDetail(SaveGoalDetailRequestDto requestDto) {
        this.startDate = requestDto.getStartDate();
        this.endDate = requestDto.getEndDate();
        this.startTime = requestDto.getStartTime();
        this.endTime = requestDto.getEndTime();
        this.notification = requestDto.isNotification();
    }
}
