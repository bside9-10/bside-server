package com.bside.study.goal.service;

import com.bside.study.goal.dto.GoalResponse;
import com.bside.study.goal.entity.Goal;
import com.bside.study.goal.repository.GoalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GoalService {

    private final GoalRepository goalRepository;

    public List<GoalResponse> findGoalsByUserId(Long userId) {
        return goalRepository.findByUserId(userId)
                .stream()
                .map(GoalResponse::new)
                .collect(Collectors.toList());
    }
}
