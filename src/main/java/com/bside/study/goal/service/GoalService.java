package com.bside.study.goal.service;

import com.bside.study.goal.dto.GoalResponseDto;
import com.bside.study.goal.repository.GoalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GoalService {

    private final GoalRepository goalRepository;

    public List<GoalResponseDto> findGoalsByUserId(Long userId) {
        return goalRepository.findGoalByUserId(userId);
    }
}
