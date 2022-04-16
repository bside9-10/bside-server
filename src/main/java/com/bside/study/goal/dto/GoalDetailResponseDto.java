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
    private String startTime;
    private String endTime;
    private boolean notification;

    @QueryProjection
    public GoalDetailResponseDto(Long id, String goalCategoryName, String title, LocalDate startDate, LocalDate endDate, String startTime, String endTime, boolean notification) {
        this.id = id;
        this.goalCategoryName = goalCategoryName;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.notification = notification;
    }
}
