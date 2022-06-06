package com.bside.study.goal.dto;

import com.bside.study.goal.entity.Goal;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SaveGoalCategoryResponseDto {
    private Long goalCategoryId;
    private String goalCategoryName;

    public SaveGoalCategoryResponseDto(Goal entity) {
        this.goalCategoryId = entity.getUserCategory().getId();
        this.goalCategoryName = entity.getGoalCategoryName();
    }
}
