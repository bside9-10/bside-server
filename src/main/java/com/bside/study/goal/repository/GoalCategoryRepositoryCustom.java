package com.bside.study.goal.repository;

import com.bside.study.goal.dto.GoalCategoryResponseDto;

import java.util.List;

public interface GoalCategoryRepositoryCustom {
    List<GoalCategoryResponseDto> findGoalCategoryLimit6();
}
