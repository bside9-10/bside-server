package com.bside.study.goal.service;

import com.bside.study.goal.dto.GoalCategoryResponseDto;
import com.bside.study.goal.repository.GoalCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GoalCategoryService {

    private final GoalCategoryRepository goalCategoryRepository;

    public List<GoalCategoryResponseDto> findGoalCategories() {
        return goalCategoryRepository.findGoalCategories();
    }
}
