package com.bside.study.goal.repository;

import com.bside.study.goal.entity.GoalCategoryDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoalCategoryDetailRepository extends JpaRepository<GoalCategoryDetail, Long>, GoalCategoryDetailRepositoryCustom {
}
