package com.bside.study.goal.service;

import com.bside.study.errors.ResourceNotFoundException;
import com.bside.study.goal.dto.GoalDetailResponseDto;
import com.bside.study.goal.dto.SaveGoalDetailRequestDto;
import com.bside.study.goal.dto.SaveGoalDetailResponseDto;
import com.bside.study.goal.entity.Goal;
import com.bside.study.goal.entity.GoalDetail;
import com.bside.study.goal.entity.GoalStatus;
import com.bside.study.goal.repository.GoalDetailRepository;
import com.bside.study.goal.repository.GoalRepository;
import com.bside.study.security.CustomUserDetailsService;
import com.bside.study.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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

        //현재 목표 진행상태를 체크
        GoalStatus goalStatus = getCurrentGoalStatus(requestDto);

        GoalDetail goalDetail = GoalDetail.builder()
                .goal(goal)
                .user(user)
                .title(requestDto.getTitle())
                .startDate(requestDto.getStartDate())
                .endDate(requestDto.getEndDate())
                .startTime(LocalTime.of(9, 30))
                .endTime(LocalTime.of(18, 30))
                .notification(requestDto.isNotification())
                .goalStatus(goalStatus)
                .build();

        GoalDetail saveGoalDetail = goalDetailRepository.save(goalDetail);
        return modelMapper.map(saveGoalDetail, SaveGoalDetailResponseDto.class);
    }

    private GoalStatus getCurrentGoalStatus(SaveGoalDetailRequestDto requestDto) {
        LocalDate now = LocalDate.now();
        LocalDate startDate = requestDto.getStartDate();

        GoalStatus goalStatus;
        if (now.isEqual(startDate)) {
            goalStatus = GoalStatus.RUN;
        } else if (now.isBefore(startDate)) {
            goalStatus = GoalStatus.BEFORE;
        } else {
            goalStatus = GoalStatus.AFTER;
        }

        return goalStatus;
    }

    public void deleteGoalDetail(Long goalDetailId, Long userId) {
        goalDetailRepository.deleteByGoalDetailIdAndUserId(goalDetailId, userId);
    }
}
