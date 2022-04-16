package com.bside.study.goal.service;

import com.bside.study.errors.ResourceNotFoundException;
import com.bside.study.goal.dto.GoalDetailResponseDto;
import com.bside.study.goal.dto.SaveGoalDetailRequestDto;
import com.bside.study.goal.dto.SaveGoalDetailResponseDto;
import com.bside.study.goal.entity.Goal;
import com.bside.study.goal.entity.GoalDetail;
import com.bside.study.goal.repository.GoalDetailRepository;
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
public class GoalDetailService {

    private final ModelMapper modelMapper;
    private final CustomUserDetailsService userDetailsService;
    private final GoalDetailRepository goalDetailRepository;
    private final GoalRepository goalRepository;

    public List<GoalDetailResponseDto> findGoalDetailByUserId(Long userId) {
        return goalDetailRepository.findGoalDetailByUserId(userId);
    }

    public SaveGoalDetailResponseDto saveGoalDetail(Long userId, SaveGoalDetailRequestDto requestDto) {
        User user = userDetailsService.loadUserById(userId);

        Long goalId = requestDto.getGoalId();
        Goal goal = goalRepository.findById(goalId)
                .orElseThrow(() -> new ResourceNotFoundException("Goal", "id", goalId));

        GoalDetail goalDetail = GoalDetail.builder()
                .goal(goal)
                .user(user)
                .title(requestDto.getTitle())
                .startDate(requestDto.getStartDate())
                .endDate(requestDto.getEndDate())
                .startTime("0930")
                .endTime("1830")
                .notification(requestDto.isNotification())
                .build();

        GoalDetail saveGoalDetail = goalDetailRepository.save(goalDetail);
        return modelMapper.map(saveGoalDetail, SaveGoalDetailResponseDto.class);
    }

    public void deleteGoalDetail(Long goalDetailId) {
        goalDetailRepository.deleteById(goalDetailId);
    }

    public void updateGoalDetail(Long goalDetailId, SaveGoalDetailRequestDto requestDto) {
        GoalDetail goalDetail = goalDetailRepository.findById(goalDetailId)
                .orElseThrow(() -> new ResourceNotFoundException("GoalDetail", "id", goalDetailId));

        goalDetail.updateGoalDetail(requestDto);
        goalDetailRepository.save(goalDetail);
    }
}
