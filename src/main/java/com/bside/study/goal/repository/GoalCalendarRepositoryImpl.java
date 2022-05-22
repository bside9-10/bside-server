package com.bside.study.goal.repository;

import com.bside.study.goal.entity.GoalCalendar;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;

import java.time.LocalDate;

import static com.bside.study.goal.entity.QGoalCalendar.goalCalendar;

public class GoalCalendarRepositoryImpl implements GoalCalendarRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public GoalCalendarRepositoryImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public GoalCalendar findByGoalDetailIdAndGoalDate(Long goalDetailId, String date) {
        return queryFactory
                .select(goalCalendar)
                .from(goalCalendar)
                .where(
                        goalCalendar.goalDetail.id.eq(goalDetailId)
                                .and(Expressions.dateTemplate(LocalDate.class, date).eq(goalCalendar.goalDate))
                )
                .fetchOne();
    }

    @Override
    public Long countGoalCalendarByUserIdAndCompleted(Long userId) {
        return queryFactory
                .select(goalCalendar.count())
                .from(goalCalendar)
                .where(goalCalendar.goalDetail.user.id.eq(userId).and(goalCalendar.completed.eq(true)))
                .fetchOne();
    }

}
