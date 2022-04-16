package com.bside.study.goal.repository;

import com.bside.study.goal.entity.GoalDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoalDetailRepository extends JpaRepository<GoalDetail, Long>, GoalDetailRepositoryCustom {
}
