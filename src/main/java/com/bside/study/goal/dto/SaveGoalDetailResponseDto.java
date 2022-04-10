package com.bside.study.goal.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SaveGoalDetailResponseDto {
    private Long id;
    private String title;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private boolean notification;

}
