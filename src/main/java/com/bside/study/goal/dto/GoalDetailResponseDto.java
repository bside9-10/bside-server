package com.bside.study.goal.dto;

import com.bside.study.goal.entity.GoalStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GoalDetailResponseDto {
    private Long id;
    private String goalCategoryName;
    private String title;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private boolean notification;
    private GoalStatus goalStatus;

    @QueryProjection
    public GoalDetailResponseDto(Long id, String goalCategoryName, String title, LocalDate startDate, LocalDate endDate, LocalTime startTime, LocalTime endTime, boolean notification, GoalStatus goalStatus) {
        this.id = id;
        this.goalCategoryName = goalCategoryName;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.notification = notification;
        this.goalStatus = goalStatus;
    }
}
