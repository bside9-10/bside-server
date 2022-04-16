package com.bside.study.goal.repository;

import com.bside.study.goal.dto.GoalResponseDto;
import com.bside.study.goal.dto.QGoalResponseDto;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.util.List;

import static com.bside.study.goal.entity.QGoal.goal;

public class GoalRepositoryImpl implements GoalRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public GoalRepositoryImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public List<GoalResponseDto> findGoalByUserId(Long userId) {
        return queryFactory
                .select(
                        new QGoalResponseDto(
                                goal.id,
                                goal.user.name,
                                goal.goalCategory.category
                        )
                )
                .from(goal)
                .where(goal.user.id.eq(userId))
                .fetch();
    }

}
