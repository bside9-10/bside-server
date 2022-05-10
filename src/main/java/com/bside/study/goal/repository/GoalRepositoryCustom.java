package com.bside.study.goal.repository;

import com.bside.study.goal.dto.GoalCategoryResponseDto;
import com.bside.study.goal.dto.LafflesResponseDto;
import com.bside.study.user.entity.User;

import java.util.List;

public interface GoalRepositoryCustom {
    List<GoalCategoryResponseDto> findGoalByUserId(Long userId);

    List<LafflesResponseDto> findGoalsByUserIdAndCategoryId(User user, Long categoryId);
}
