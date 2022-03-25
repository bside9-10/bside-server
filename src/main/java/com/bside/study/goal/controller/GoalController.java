package com.bside.study.goal.controller;

import com.bside.study.common.api.ApiResult;
import com.bside.study.goal.entity.Goal;
import com.bside.study.goal.service.GoalService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.bside.study.common.api.ApiUtils.success;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class GoalController {

    private final GoalService goalService;

    @GetMapping("/goals")
    public ApiResult<List<Goal>> findAll() {
        return success(goalService.findAll());
    }

}
