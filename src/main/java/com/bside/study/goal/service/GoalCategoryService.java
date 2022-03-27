package com.bside.study.goal.service;

import com.bside.study.goal.dto.GoalCategoryResponse;
import com.bside.study.goal.entity.GoalCategory;
import com.bside.study.goal.repository.GoalCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GoalCategoryService {

    private final GoalCategoryRepository goalCategoryRepository;

    public List<GoalCategoryResponse> findAll() {
        return goalCategoryRepository.findAll()
                .stream()
                .map(GoalCategoryResponse::new)
                .collect(Collectors.toList());
    }
}
