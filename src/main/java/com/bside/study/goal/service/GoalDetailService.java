package com.bside.study.goal.service;

import com.bside.study.errors.ResourceNotFoundException;
import com.bside.study.goal.dto.GoalDetailResponseDto;
import com.bside.study.goal.dto.SaveGoalDetailRequestDto;
import com.bside.study.goal.dto.SaveGoalDetailResponseDto;
import com.bside.study.goal.entity.Goal;
import com.bside.study.goal.entity.GoalCalendar;
import com.bside.study.goal.entity.GoalDateStatus;
import com.bside.study.goal.entity.GoalDetail;
import com.bside.study.goal.repository.GoalCalendarRepository;
import com.bside.study.goal.repository.GoalDetailRepository;
import com.bside.study.goal.repository.GoalRepository;
import com.bside.study.security.CustomUserDetailsService;
import com.bside.study.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@Transactional
public class GoalDetailService {

    private final ModelMapper modelMapper;
    private final CustomUserDetailsService userDetailsService;
    private final GoalDetailRepository goalDetailRepository;
    private final GoalRepository goalRepository;
    private final GoalCalendarRepository goalCalendarRepository;

    public List<GoalDetailResponseDto> findGoalDetailByUserId(Long userId) {
        return goalDetailRepository.findGoalDetailByUserId(userId);
    }

    public SaveGoalDetailResponseDto saveGoalDetail(Long userId, SaveGoalDetailRequestDto requestDto) {
        User user = userDetailsService.loadUserById(userId);

        Long goalId = requestDto.getGoalId();
        Goal goal = goalRepository.findById(goalId)
                .orElseThrow(() -> new ResourceNotFoundException("Goal", "id", goalId));

        GoalDetail saveGoalDetail = saveGoalDetail(requestDto, user, goal);

        GoalDateStatus goalDateStatus = requestDto.getGoalDateStatus();

        LocalDate startDate = requestDto.getStartDate();
        LocalDate endDate = requestDto.getEndDate().plusDays(1);
        Stream<LocalDate> localDateStream = startDate.datesUntil(endDate);

        if (GoalDateStatus.DAY == goalDateStatus) {
            localDateStream.forEach(localDate -> {
                if (DayOfWeek.SATURDAY != localDate.getDayOfWeek() && DayOfWeek.SUNDAY != localDate.getDayOfWeek()) {
                    saveGoalCalendar(saveGoalDetail, localDate);
                }
            });

        } else if (GoalDateStatus.DAILY == goalDateStatus) {
            localDateStream.forEach(localDate -> saveGoalCalendar(saveGoalDetail, localDate));

        } else if (GoalDateStatus.WEEKEND == goalDateStatus) {
            localDateStream.forEach(localDate -> {
                if (DayOfWeek.SATURDAY == localDate.getDayOfWeek() || DayOfWeek.SUNDAY == localDate.getDayOfWeek()) {
                    saveGoalCalendar(saveGoalDetail, localDate);
                }
            });
        }

        return modelMapper.map(saveGoalDetail, SaveGoalDetailResponseDto.class);
    }

    private GoalDetail saveGoalDetail(SaveGoalDetailRequestDto requestDto, User user, Goal goal) {
        GoalDetail goalDetail = GoalDetail.builder()
                .goal(goal)
                .user(user)
                .title(requestDto.getTitle())                   // 세부목표
                .startDate(requestDto.getStartDate())           // 시작날짜
                .endDate(requestDto.getEndDate())               // 종료날짜
                .startTime(requestDto.getStartTime())           // 시작시간
                .endTime(requestDto.getEndTime())               // 종료시간
                .notification(requestDto.isNotification())      // 알림여부
                .goalDateStatus(requestDto.getGoalDateStatus()) // 매일, 주말, 평일여부
                .build();

        return goalDetailRepository.save(goalDetail);
    }

    private void saveGoalCalendar(GoalDetail saveGoalDetail, LocalDate localDate) {
        GoalCalendar goalCalendar = GoalCalendar.builder()
                .goalDetail(saveGoalDetail)
                .completed(false)
                .goalDate(localDate)
                .build();

        goalCalendarRepository.save(goalCalendar);
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
