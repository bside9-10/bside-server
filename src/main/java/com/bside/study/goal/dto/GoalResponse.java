package com.bside.study.goal.dto;

import com.bside.study.goal.entity.Goal;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GoalResponse {
    private String name;
    private String email;
    private String goalCategoryName;

    public GoalResponse(Goal entity) {
        this.name = entity.getUser().getName();
        this.email = entity.getUser().getEmail();
        this.goalCategoryName = entity.getGoalCategory().getGoalCategoryName();
    }
}
