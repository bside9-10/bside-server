package com.bside.study.goal.repository;

import com.bside.study.goal.dto.GoalCategoryDetailResponseDto;

import java.util.List;

public interface GoalCategoryDetailRepositoryCustom {
    List<GoalCategoryDetailResponseDto> findGoalCategoryDetailLimit6();
}
