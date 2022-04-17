package com.bside.study.goal.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GoalResponseDto {
    private Long goalId;
    private String category;

    private LocalDateTime createdDate;

    @QueryProjection
    public GoalResponseDto(Long goalId, String category, LocalDateTime createdDate) {
        this.goalId = goalId;
        this.category = category;
        this.createdDate = createdDate;
    }
}
