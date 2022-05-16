package com.bside.study.user.controller;

import com.bside.study.common.api.ApiResult;
import com.bside.study.errors.ResourceNotFoundException;
import com.bside.study.goal.entity.GoalAvailableTime;
import com.bside.study.goal.repository.GoalAvailableTimeRepository;
import com.bside.study.goal.repository.GoalDetailRepository;
import com.bside.study.security.CurrentUser;
import com.bside.study.security.UserPrincipal;
import com.bside.study.user.dto.UserInfoResponse;
import com.bside.study.user.entity.User;
import com.bside.study.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

import static com.bside.study.common.api.ApiUtils.success;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UserController {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final GoalDetailRepository goalDetailRepository;
    private final GoalAvailableTimeRepository goalAvailableTimeRepository;

    @GetMapping("/users/{userId}/me")
    public  ApiResult<UserInfoResponse> getCurrentUser(
            @CurrentUser UserPrincipal userPrincipal,
            @PathVariable Long userId) {

        if (!Objects.equals(userPrincipal.getId(), userId)) {
            throw new AccessDeniedException("잘못된 접근입니다.");
        }

        User user = userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));

        GoalAvailableTime goalAvailableTime = goalAvailableTimeRepository.findByUserId(userId);

        UserInfoResponse userInfoResponse = modelMapper.map(user, UserInfoResponse.class);

        userInfoResponse.calcTotalGoalAvailableTime(goalAvailableTime.getDaily(), goalAvailableTime.getWeekly());
        userInfoResponse.setGoalCount(goalDetailRepository.countByUserId(user.getId()));

        return success(userInfoResponse);
    }
}
