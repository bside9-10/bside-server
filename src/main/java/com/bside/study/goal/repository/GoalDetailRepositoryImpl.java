package com.bside.study.goal.repository;

import com.bside.study.goal.dto.GoalDetailResponseDto;
import com.bside.study.goal.dto.QGoalDetailResponseDto;
import com.bside.study.goal.dto.QTodayGoalDetailDto;
import com.bside.study.goal.dto.TodayGoalDetailDto;
import com.bside.study.goal.entity.Goal;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;

import static com.bside.study.goal.entity.QGoalCalendar.goalCalendar;
import static com.bside.study.goal.entity.QGoalDetail.goalDetail;

public class GoalDetailRepositoryImpl implements GoalDetailRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public GoalDetailRepositoryImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public List<GoalDetailResponseDto> findGoalDetailByUserId(Long userId) {
        return queryFactory
                .select(
                        new QGoalDetailResponseDto(
                                goalDetail.id,
                                goalDetail.goal.goalCategoryName,
                                goalDetail.title,
                                goalDetail.startDate,
                                goalDetail.endDate,
                                goalDetail.startTime,
                                goalDetail.endTime,
                                goalDetail.notification
                        )
                )
                .from(goalDetail)
                .fetch();
    }

    @Override
    public List<TodayGoalDetailDto> findTodayGoalDetailByGoalId(Goal goal, String date) {
        return queryFactory
                .select(
                        new QTodayGoalDetailDto(
                                goalDetail.id,
                                goalDetail.title,
                                goalDetail.startTime,
                                goalDetail.goalDateStatus
                        )
                )
                .from(goalDetail)
                .where(
                        goalDetail.goal.id.eq(goal.getId())
                                .and(goalDetail.user.id.eq(goal.getUser().getId()))
                                .and(Expressions.dateTemplate(LocalDate.class, date).between(goalDetail.startDate, goalDetail.endDate))
                                .and(
                                        JPAExpressions.selectOne()
                                                .from(goalCalendar)
                                                .where(
                                                        goalCalendar.goalDetail.id.eq(goalDetail.id)
                                                                .and(goalCalendar.goalDate.eq(Expressions.dateTemplate(LocalDate.class, date)))
                                                ).exists()
                                )
                )
                .fetch();
    }

}
