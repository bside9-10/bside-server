package com.bside.study.goal.repository;

import com.bside.study.goal.dto.GoalDetailResponseDto;

import java.util.List;

public interface GoalDetailRepositoryCustom {
    List<GoalDetailResponseDto> findGoalDetailByUserId(Long userId);

    void findGoalDetailByGoalIdBetween(String date);
}
