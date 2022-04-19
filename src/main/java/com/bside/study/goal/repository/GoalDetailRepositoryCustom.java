package com.bside.study.goal.repository;

import com.bside.study.goal.dto.GoalDetailResponseDto;
import com.bside.study.goal.dto.TodayGoalDetailDto;
import com.bside.study.goal.entity.Goal;

import java.util.List;

public interface GoalDetailRepositoryCustom {
    List<GoalDetailResponseDto> findGoalDetailByUserId(Long userId);

    List<TodayGoalDetailDto> findTodayGoalDetailByGoalId(Goal goal, String date);
}
