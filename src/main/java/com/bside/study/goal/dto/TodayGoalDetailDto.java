package com.bside.study.goal.dto;

import com.bside.study.goal.entity.GoalDateStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TodayGoalDetailDto {

    private Long goalDetailId;
    private String title;
    private String startTime;
    private String goalDateStatusDesc;

    @QueryProjection
    public TodayGoalDetailDto(Long goalDetailId, String title, LocalTime startTime, GoalDateStatus goalDateStatus) {
        this.goalDetailId = goalDetailId;
        this.title = title;
        this.startTime = startTime.format(DateTimeFormatter.ofPattern("a hh:mm"));
        this.goalDateStatusDesc = goalDateStatus.getDesc();
    }
}
