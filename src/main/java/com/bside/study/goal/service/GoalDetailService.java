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
import java.time.LocalTime;
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

        if (goalDateStatus == null) {
            // ????????? ?????? ????????? ??????
            List<DayOfWeek> dayOfWeeks = requestDto.getDayOfWeeks();

            localDateStream.forEach(localDate -> {
                for (DayOfWeek dayOfWeek : dayOfWeeks) {
                    if (dayOfWeek == localDate.getDayOfWeek()) {
                        saveGoalCalendar(saveGoalDetail, localDate);
                    }
                }
            });

        } else {
            // ??????, ??????, ????????? ????????? ??????
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
        }

        return modelMapper.map(saveGoalDetail, SaveGoalDetailResponseDto.class);
    }

    private GoalDetail saveGoalDetail(SaveGoalDetailRequestDto requestDto, User user, Goal goal) {
        GoalDetail goalDetail = GoalDetail.builder()
                .goal(goal)
                .user(user)
                .title(requestDto.getTitle())                   // ????????????
                .startDate(requestDto.getStartDate())           // ????????????
                .endDate(requestDto.getEndDate())               // ????????????
                .startTime(LocalTime.of(Integer.parseInt(requestDto.getStartTime().substring(0, 2)), Integer.parseInt(requestDto.getStartTime().substring(2, 4))))           // ????????????
                .endTime(LocalTime.of(Integer.parseInt(requestDto.getEndTime().substring(0, 2)), Integer.parseInt(requestDto.getEndTime().substring(2, 4))))               // ????????????
                .notification(requestDto.isNotification())      // ????????????
                .goalDateStatus(requestDto.getGoalDateStatus()) // ??????, ??????, ????????????
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

    public void checkTodayGoalCompleted(Long userId, Long goalDetailId, String date) {
        GoalCalendar goalCalendar = goalCalendarRepository.findByGoalDetailIdAndGoalDate(goalDetailId, date);
        goalCalendar.checkCompleted();
    }
}
