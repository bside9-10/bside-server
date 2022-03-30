package com.bside.study.goal.repository;

import com.bside.study.goal.dto.GoalResponseDto;

import java.util.List;

public interface GoalRepositoryCustom {
    List<GoalResponseDto> findGoalByUserId(Long userId);
}
