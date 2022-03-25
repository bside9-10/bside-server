package com.bside.study.goal.service;

import com.bside.study.goal.entity.Goal;
import com.bside.study.goal.repository.GoalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GoalService {

    private final GoalRepository goalRepository;

    public List<Goal> findAll() {
        return goalRepository.findAll();
    }
}
