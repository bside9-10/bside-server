package com.bside.study.goal.dto;

import com.bside.study.goal.entity.GoalCategory;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GoalCategoryResponse {
    private Long id;
    private String goalCategoryName;

    public GoalCategoryResponse(GoalCategory entity) {
        this.id = entity.getId();
        this.goalCategoryName = entity.getGoalCategoryName();
    }
}
