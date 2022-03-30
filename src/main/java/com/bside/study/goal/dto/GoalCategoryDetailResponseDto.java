package com.bside.study.goal.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GoalCategoryDetailResponseDto {
    private Long id;
    private String detail;
    private String category;

    @QueryProjection
    public GoalCategoryDetailResponseDto(Long id, String detail, String category) {
        this.id = id;
        this.detail = detail;
        this.category = category;
    }
}
