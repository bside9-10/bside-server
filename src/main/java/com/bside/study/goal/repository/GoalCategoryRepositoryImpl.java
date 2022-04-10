package com.bside.study.goal.repository;

import com.bside.study.goal.dto.GoalCategoryResponseDto;
import com.bside.study.goal.dto.QGoalCategoryResponseDto;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.util.List;

import static com.bside.study.goal.entity.QGoalCategory.goalCategory;

public class GoalCategoryRepositoryImpl implements GoalCategoryRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public GoalCategoryRepositoryImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public List<GoalCategoryResponseDto> findGoalCategories() {
        return queryFactory
                .select(
                        new QGoalCategoryResponseDto(
                                goalCategory.id,
                                goalCategory.category
                        )
                )
                .from(goalCategory)
                .fetch();
    }

}
