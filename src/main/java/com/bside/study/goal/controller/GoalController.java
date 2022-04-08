package com.bside.study.goal.controller;

import com.bside.study.common.api.ApiResult;
import com.bside.study.goal.dto.*;
import com.bside.study.goal.service.GoalCategoryDetailService;
import com.bside.study.goal.service.GoalCategoryService;
import com.bside.study.goal.service.GoalService;
import com.bside.study.security.CurrentUser;
import com.bside.study.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.bside.study.common.api.ApiUtils.success;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class GoalController {

    private final ModelMapper modelMapper;
    private final GoalService goalService;
    private final GoalCategoryService goalCategoryService;
    private final GoalCategoryDetailService goalCategoryDetailService;


    @GetMapping("/goals/{userId}")
    public ApiResult<List<GoalResponseDto>> findGoalsByUserId(@PathVariable("userId") Long userId) {

        List<GoalResponseDto> goalsByUserId = goalService.findGoalsByUserId(userId);
        return success(goalsByUserId);
    }

    @GetMapping("/goals/categories")
    public ApiResult<List<GoalCategoryResponseDto>> findGoalCategories() {
        return success(goalCategoryService.findGoalCategoryLimit6());
    }

    @PostMapping("/goals/{userId}/categories")
    public ApiResult<SaveGoalCategoryResponseDto> saveGoalCategory(
            @PathVariable("userId") Long userId,
            @Valid @RequestBody SaveGoalCategoryRequestDto saveGoalCategoryRequestDto) {

        return success(goalService.saveGoalCategory(userId, saveGoalCategoryRequestDto));
    }

    @GetMapping("/goals/categories/details")
    public ApiResult<List<GoalCategoryDetailResponseDto>> findGoalCategoryDetails() {
        return success(goalCategoryDetailService.findGoalCategoryDetailLimit6());
    }



}
