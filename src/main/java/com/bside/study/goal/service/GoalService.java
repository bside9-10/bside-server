package com.bside.study.goal.service;

import com.bside.study.errors.ResourceNotFoundException;
import com.bside.study.goal.dto.*;
import com.bside.study.goal.entity.Goal;
import com.bside.study.goal.entity.GoalAvailableTime;
import com.bside.study.goal.entity.GoalCategory;
import com.bside.study.goal.repository.GoalAvailableTimeRepository;
import com.bside.study.goal.repository.GoalCategoryRepository;
import com.bside.study.goal.repository.GoalDetailRepository;
import com.bside.study.goal.repository.GoalRepository;
import com.bside.study.security.CustomUserDetailsService;
import com.bside.study.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class GoalService {

    private final ModelMapper modelMapper;
    private final CustomUserDetailsService userDetailsService;
    private final GoalRepository goalRepository;
    private final GoalDetailRepository goalDetailRepository;
    private final GoalCategoryRepository goalCategoryRepository;
    private final GoalAvailableTimeRepository goalAvailableTimeRepository;

    public SaveGoalCategoryResponseDto saveGoalCategory(Long userId, SaveGoalCategoryRequestDto requestDto) {
        User user = userDetailsService.loadUserById(userId);

        Long goalCategoryId = requestDto.getGoalCategoryId();
        GoalCategory goalCategory = goalCategoryRepository.findById(goalCategoryId)
                .orElseThrow(() -> new ResourceNotFoundException("GoalCategory", "id", goalCategoryId));

        Goal saveGoal = goalRepository.save(requestDto.toEntity(user, goalCategory));
        return new SaveGoalCategoryResponseDto(saveGoal);
    }

    public void saveGoalAvailableTime(Long userId, SaveGoalAvailableTimeRequestDto requestDto) {
        User user = userDetailsService.loadUserById(userId);

        GoalAvailableTime goalAvailableTime = GoalAvailableTime.builder()
                .daily(requestDto.getDaily())
                .weekly(requestDto.getWeekly())
                .user(user)
                .build();

        goalAvailableTimeRepository.save(goalAvailableTime);
    }

    public List<TodayGoalResponseDto> findTodayGoalsByUserId(Long userId, String date) {
        User user = userDetailsService.loadUserById(userId);
        List<Goal> findGoals = goalRepository.findByUserId(user.getId());

        List<TodayGoalResponseDto> result = new ArrayList<>();

        findGoals.forEach(goal -> {
            // 목표 기간 중인 경우 조회, 매일 평일 주말에 해당하는 날짜만 조회
            List<TodayGoalDetailDto> goalDetails = goalDetailRepository.findTodayGoalDetailByGoalId(goal, date);

            result.add(new TodayGoalResponseDto(goal, goalDetails));
        });

        return result;
    }

    public List<LafflesResponseDto> findGoalsByUserIdAndCategoryId(Long userId, Long categoryId) {
        User user = userDetailsService.loadUserById(userId);

        return goalRepository.findGoalsByUserIdAndCategoryId(user, categoryId);
    }

    public List<GoalCategoryResponseDto> findGoalCategoriesByUserId(Long userId) {
        User user = userDetailsService.loadUserById(userId);

        return goalRepository.findGoalByUserId(user.getId());
    }

    public GoalAvailableTimeDto findGoalAvailableTimeByUserId(Long userId) {
        GoalAvailableTime goalAvailableTime = goalAvailableTimeRepository.findByUserId(userId);

        return new GoalAvailableTimeDto(goalAvailableTime);
    }

    public List<StampDto> findTodayGoalsCalendarStampByUserId(Long userId, String month) {
        return goalRepository.findTodayGoalsCalendarStampByUserId(userId, month);
    }
}
