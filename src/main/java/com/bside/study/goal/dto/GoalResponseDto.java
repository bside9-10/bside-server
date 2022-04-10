package com.bside.study.goal.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GoalResponseDto {
    private Long id;
    private String name;
    private String category;

    @QueryProjection
    public GoalResponseDto(Long id, String name, String category) {
        this.id = id;
        this.name = name;
        this.category = category;
    }
}
