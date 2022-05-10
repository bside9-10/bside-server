package com.bside.study.goal.repository;

import com.bside.study.goal.dto.GoalCategoryResponseDto;
import com.bside.study.goal.dto.LafflesResponseDto;
import com.bside.study.goal.dto.QGoalCategoryResponseDto;
import com.bside.study.goal.dto.QLafflesResponseDto;
import com.bside.study.user.entity.User;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.util.List;

import static com.bside.study.goal.entity.QGoal.goal;
import static com.bside.study.goal.entity.QGoalCalendar.goalCalendar;
import static com.bside.study.goal.entity.QGoalDetail.goalDetail;

public class GoalRepositoryImpl implements GoalRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public GoalRepositoryImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public List<GoalCategoryResponseDto> findGoalByUserId(Long userId) {
        return queryFactory
                .select(
                        new QGoalCategoryResponseDto(
                                goal.goalCategory.id,
                                goal.goalCategoryName
                        )
                )
                .from(goal)
                .where(goal.user.id.eq(userId))
                .fetch();
    }

    @Override
    public List<LafflesResponseDto> findGoalsByUserIdAndCategoryId(User user, Long categoryId) {
        return queryFactory
                .select(
                        new QLafflesResponseDto(
                                goalDetail.id,
                                goalDetail.title,
                                goalDetail.startDate,
                                goalDetail.endDate,
                                JPAExpressions.select(goalCalendar.completed.count())
                                        .from(goalCalendar)
                                        .where(goalCalendar.completed.eq(true).and(goalCalendar.goalDetail.id.eq(goalDetail.id))),
                                JPAExpressions.select(goalCalendar.completed.count())
                                        .from(goalCalendar)
                                        .where(goalCalendar.completed.eq(false).and(goalCalendar.goalDetail.id.eq(goalDetail.id)))
                        )
                )
                .from(goalDetail)
                .join(goalDetail.goal, goal).on(goal.user.id.eq(user.getId()).and(goal.goalCategory.id.eq(categoryId)))
                .fetch();
    }

}
