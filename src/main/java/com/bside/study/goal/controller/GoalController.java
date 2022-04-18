package com.bside.study.goal.controller;

import com.bside.study.common.api.ApiResult;
import com.bside.study.goal.dto.*;
import com.bside.study.goal.service.GoalCategoryService;
import com.bside.study.goal.service.GoalDetailService;
import com.bside.study.goal.service.GoalService;
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
    private final GoalDetailService goalDetailService;

    /**
     * 라플 리스트 조회
     *
     * @param userId 사용자 아이디
     * @return List
     */
    @GetMapping("/goals/{userId}")
    public ApiResult<List<GoalResponseDto>> findGoalsByUserId(@PathVariable("userId") Long userId) {
        List<GoalResponseDto> goalsByUserId = goalService.findGoalsByUserId(userId);
        return success(goalsByUserId);
    }

    /**
     * 라플 카테고리 조회
     *
     * @return List
     */
    @GetMapping("/goals/categories")
    public ApiResult<List<GoalCategoryResponseDto>> findGoalCategories() {
        return success(goalCategoryService.findGoalCategories());
    }

    /**
     * 목표 저장
     *
     * @param userId     사용자 아이디
     * @param requestDto 목표 DTO
     * @return SaveGoalCategoryResponseDto
     */
    @PostMapping("/goals/{userId}/categories")
    public ApiResult<SaveGoalCategoryResponseDto> saveGoalCategory(
            @PathVariable("userId") Long userId,
            @Valid @RequestBody SaveGoalCategoryRequestDto requestDto) {
        return success(goalService.saveGoalCategory(userId, requestDto));
    }

    /**
     * 상세 목표 리스트 조회
     *
     * @param userId 사용자 아이디
     * @return List
     */
    @GetMapping("/goals/{userId}/details")
    public ApiResult<List<GoalDetailResponseDto>> findGoalDetailByUserId(@PathVariable("userId") Long userId) {
        return success(goalDetailService.findGoalDetailByUserId(userId));
    }

    /**
     * 세부 목표 저장
     *
     * @param userId     사용자 아이디
     * @param requestDto 목표 DTO
     * @return SaveGoalDetailResponseDto
     */
    @PostMapping("/goals/{userId}/details")
    public ApiResult<SaveGoalDetailResponseDto> saveGoalDetail(
            @PathVariable("userId") Long userId,
            @Valid @RequestBody SaveGoalDetailRequestDto requestDto) {
        return success(goalDetailService.saveGoalDetail(userId, requestDto));
    }

    /**
     * 세부 목표 삭제
     *
     * @param userId       사용자 아이디
     * @param goalDetailId 세부 목표 아이디
     * @return String
     */
    @DeleteMapping("/goals/{userId}/details/{goalDetailId}")
    public ApiResult<String> deleteGoalDetail(
            @PathVariable("userId") Long userId,
            @PathVariable("goalDetailId") Long goalDetailId) {
        goalDetailService.deleteGoalDetail(goalDetailId);
        return success("success delete goalDetailId : " + goalDetailId);
    }

    /**
     * 세부 목표 변경
     *
     * @param userId 사용자 아이디
     * @param goalDetailId 세부 목표 아이디
     * @param requestDto 목표 DTO
     * @return String
     */
    @PutMapping("/goals/{userId}/details/{goalDetailId}")
    public ApiResult<String> updateGoalDetail(
            @PathVariable("userId") Long userId,
            @PathVariable("goalDetailId") Long goalDetailId,
            @Valid @RequestBody SaveGoalDetailRequestDto requestDto) {
        goalDetailService.updateGoalDetail(goalDetailId, requestDto);
        return success("success update goalDetailId : " + goalDetailId);
    }

    /**
     * 실천 가능 시간 저장
     *
     * @param userId 사용자 아이디
     * @param requestDto 목표 DTO
     * @return String
     */
    @PostMapping("/goals/{userId}/available-time")
    public ApiResult<String> saveGoalAvailableTime(
            @PathVariable("userId") Long userId,
            @Valid @RequestBody SaveGoalAvailableTimeRequestDto requestDto) {
        goalService.saveGoalAvailableTime(userId, requestDto);
        return success("success save");
    }

    /**
     * 오늘의 라플 리스트 조회
     *
     * @param userId 사용자 아이디
     * @param date 조회 날짜
     * @return String
     */
    @GetMapping("/goals/{userId}/today")
    public ApiResult<List<TodayGoalResponseDto>> findTodayGoalsByUserId(
            @PathVariable("userId") Long userId,
            @RequestParam(name = "date") String date) {

        return success(goalService.findTodayGoalsByUserId(userId, date));
    }

    /**
     * 오늘의 라플 체크
     *
     * @param userId 사용자 아이디
     * @param goalDetailId 세부 목표 아이디
     * @param date 조회 날짜
     * @return String
     */
    @PostMapping("/goals/{userId}/today/{goalDetailId}")
    public ApiResult<String> checkTodayGoalCompleted(
            @PathVariable("userId") Long userId,
            @PathVariable("goalDetailId") Long goalDetailId,
            @RequestParam(name = "date") String date) {

        goalDetailService.checkTodayGoalCompleted(userId, goalDetailId, date);
        return success("success save");
    }

}
