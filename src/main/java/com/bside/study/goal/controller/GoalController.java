package com.bside.study.goal.controller;

import com.bside.study.common.api.ApiResult;
import com.bside.study.goal.dto.GoalCategoryResponse;
import com.bside.study.goal.dto.GoalResponse;
import com.bside.study.goal.service.GoalCategoryService;
import com.bside.study.goal.service.GoalService;
import com.bside.study.security.CurrentUser;
import com.bside.study.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.bside.study.common.api.ApiUtils.success;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class GoalController {

    private final ModelMapper modelMapper;
    private final GoalService goalService;
    private final GoalCategoryService goalCategoryService;

    @GetMapping("/goals")
    public ApiResult<List<GoalResponse>> findGoalsByUserId(@CurrentUser UserPrincipal userPrincipal) {
        return success(goalService.findGoalsByUserId(userPrincipal.getId()));
    }

    @GetMapping("/goals/categories")
    public ApiResult<List<GoalCategoryResponse>> findGoalCategories() {
        return success(goalCategoryService.findAll());
    }

}
