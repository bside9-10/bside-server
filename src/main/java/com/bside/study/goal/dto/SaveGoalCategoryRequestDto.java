package com.bside.study.goal.dto;

import com.bside.study.goal.entity.Goal;
import com.bside.study.goal.entity.GoalCategory;
import com.bside.study.goal.entity.UserCategory;
import com.bside.study.user.entity.User;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class SaveGoalCategoryRequestDto {

    @NotNull(message = "세부 목표를 선택해주세요")
    private Long goalCategoryId;

    private String goalCategoryName;

    public Goal toEntity(User user, GoalCategory goalCategory) {
        if (!goalCategory.getId().equals(6L)) {
            goalCategoryName = goalCategory.getCategory();
        }

        UserCategory userCategory = UserCategory.builder()
                .goalCategory(goalCategory)
                .category(goalCategoryName)
                .user(user)
                .build();

        return Goal.builder()
                .userCategory(userCategory)
                .goalCategoryName(goalCategoryName)
                .user(user)
                .build();
    }
}
