package com.bside.study.goal.dto;

import com.bside.study.goal.entity.Goal;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TodayGoalResponseDto {

    private Long goalId;
    private String category;
    private List<TodayGoalDetailDto> goalDetails;

    public TodayGoalResponseDto(Goal entity, List<TodayGoalDetailDto> goalDetails) {
        this.goalId = entity.getId();
        this.category = entity.getGoalCategoryName();
        this.goalDetails = goalDetails;
    }
}
