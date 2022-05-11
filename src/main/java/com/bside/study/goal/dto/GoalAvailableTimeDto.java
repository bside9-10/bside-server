package com.bside.study.goal.dto;

import com.bside.study.goal.entity.GoalAvailableTime;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GoalAvailableTimeDto {

    private int daily;
    private int weekly;

    public GoalAvailableTimeDto(GoalAvailableTime entity) {
        this.daily = entity.getDaily();
        this.weekly = entity.getWeekly();
    }
}
