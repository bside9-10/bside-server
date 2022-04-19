package com.bside.study.goal.repository;

import com.bside.study.goal.entity.GoalCalendar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoalCalendarRepository extends JpaRepository<GoalCalendar, Long>, GoalCalendarRepositoryCustom {
}
