package com.bside.study.goal.repository;

import com.bside.study.goal.dto.GoalCategoryDetailResponseDto;
import com.bside.study.goal.dto.QGoalCategoryDetailResponseDto;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.util.List;

import static com.bside.study.goal.entity.QGoalCategoryDetail.goalCategoryDetail;

public class GoalCategoryDetailRepositoryImpl implements GoalCategoryDetailRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public GoalCategoryDetailRepositoryImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public List<GoalCategoryDetailResponseDto> findGoalCategoryDetailLimit6() {
        return queryFactory
                .select(
                        new QGoalCategoryDetailResponseDto(
                                goalCategoryDetail.id,
                                goalCategoryDetail.detail,
                                goalCategoryDetail.goalCategory.category
                        )
                )
                .from(goalCategoryDetail)
                .limit(6)
                .fetch();
    }

}
