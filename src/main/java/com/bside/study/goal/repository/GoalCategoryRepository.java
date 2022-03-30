package com.bside.study.goal.repository;

import com.bside.study.goal.entity.GoalCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoalCategoryRepository extends JpaRepository<GoalCategory, Long>, GoalCategoryRepositoryCustom {
}
