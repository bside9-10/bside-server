package com.bside.study.goal.repository;

import com.bside.study.goal.dto.GoalDetailResponseDto;
import com.bside.study.goal.dto.QGoalDetailResponseDto;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;

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
    public void findGoalDetailByGoalIdBetween(String date) {
        queryFactory
                .select(goalDetail)
                .from(goalDetail)
                .where(
                        Expressions.dateTemplate(LocalDate.class, date).between(goalDetail.startDate, goalDetail.endDate)
                )
                .fetch();
    }

}
