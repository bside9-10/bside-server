package com.bside.study.goal.dto;

import com.bside.study.goal.entity.GoalDetail;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GoalDetailResponseDto {
    private Long goalDetailId;
    private String goalCategoryName;
    private String title;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private boolean notification;

    @QueryProjection
    public GoalDetailResponseDto(Long goalDetailId, String goalCategoryName, String title, LocalDate startDate, LocalDate endDate, LocalTime startTime, LocalTime endTime, boolean notification) {
        this.goalDetailId = goalDetailId;
        this.goalCategoryName = goalCategoryName;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.notification = notification;
    }

    public GoalDetailResponseDto(GoalDetail entity) {
        this.goalDetailId = entity.getId();
        this.goalCategoryName = entity.getGoal().getGoalCategoryName();
        this.title = entity.getTitle();
        this.startDate = entity.getStartDate();
        this.endDate = entity.getEndDate();
        this.startTime = entity.getStartTime();
        this.endTime = entity.getEndTime();
        this.notification = entity.isNotification();
    }
}
