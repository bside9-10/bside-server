package com.bside.study.goal.repository;

import com.bside.study.goal.entity.GoalCalendar;

public interface GoalCalendarRepositoryCustom {
    GoalCalendar findByGoalDetailIdAndGoalDate(Long goalDetailId, String date);
}
