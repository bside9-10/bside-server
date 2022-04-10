package com.bside.study.goal.service;

import com.bside.study.errors.ResourceNotFoundException;
import com.bside.study.goal.dto.GoalResponseDto;
import com.bside.study.goal.dto.SaveGoalAvailableTimeRequestDto;
import com.bside.study.goal.dto.SaveGoalCategoryRequestDto;
import com.bside.study.goal.dto.SaveGoalCategoryResponseDto;
import com.bside.study.goal.entity.Goal;
import com.bside.study.goal.entity.GoalAvailableTime;
import com.bside.study.goal.entity.GoalCategory;
import com.bside.study.goal.repository.GoalAvailableTimeRepository;
import com.bside.study.goal.repository.GoalCategoryRepository;
import com.bside.study.goal.repository.GoalRepository;
import com.bside.study.security.CustomUserDetailsService;
import com.bside.study.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class GoalService {

    private final CustomUserDetailsService userDetailsService;
    private final GoalRepository goalRepository;
    private final GoalCategoryRepository goalCategoryRepository;
    private final GoalAvailableTimeRepository goalAvailableTimeRepository;

    public List<GoalResponseDto> findGoalsByUserId(Long userId) {
        return goalRepository.findGoalByUserId(userId);
    }

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
}
