package com.bside.study.goal.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GoalCategoryResponseDto {
    private Long goalCategoryId;
    private String category;

    @QueryProjection
    public GoalCategoryResponseDto(Long goalCategoryId, String category) {
        this.goalCategoryId = goalCategoryId;
        this.category = category;
    }
}
