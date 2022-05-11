package com.bside.study.goal.repository;

import com.bside.study.goal.entity.GoalAvailableTime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoalAvailableTimeRepository extends JpaRepository<GoalAvailableTime, Long> {
    GoalAvailableTime findByUserId(Long userId);
}
