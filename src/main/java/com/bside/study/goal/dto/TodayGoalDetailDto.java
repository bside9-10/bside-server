package com.bside.study.goal.dto;

import com.bside.study.goal.entity.GoalDateStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TodayGoalDetailDto {

    private Long goalDetailId;
    private String title;
    private String startTime;
    private String goalDateStatusDesc;

    @QueryProjection
    public TodayGoalDetailDto(Long goalDetailId, String title, String startTime, GoalDateStatus goalDateStatus) {
        this.goalDetailId = goalDetailId;
        this.title = title;
        this.startTime = startTime;
        this.goalDateStatusDesc = goalDateStatus.getDesc();
    }
}
