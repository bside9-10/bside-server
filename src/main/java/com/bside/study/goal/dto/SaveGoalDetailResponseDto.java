package com.bside.study.goal.dto;

import com.bside.study.goal.entity.GoalDateStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SaveGoalDetailResponseDto {

    private Long id;
    private String title;
    private LocalDate startDate;
    private LocalDate endDate;
    private String startTime;
    private String endTime;
    private boolean notification;
    private GoalDateStatus goalDateStatus;

}
