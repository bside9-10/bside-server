package com.bside.study.goal.service;

import com.bside.study.goal.dto.GoalCategoryDetailResponseDto;
import com.bside.study.goal.repository.GoalCategoryDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GoalCategoryDetailService {

    private final GoalCategoryDetailRepository goalCategoryDetailRepository;

    public List<GoalCategoryDetailResponseDto> findGoalCategoryDetailLimit6() {
        return goalCategoryDetailRepository.findGoalCategoryDetailLimit6();
    }
}
