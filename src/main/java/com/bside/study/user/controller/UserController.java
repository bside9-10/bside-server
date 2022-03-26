package com.bside.study.user.controller;

import com.bside.study.common.api.ApiResult;
import com.bside.study.errors.ResourceNotFoundException;
import com.bside.study.security.CurrentUser;
import com.bside.study.security.UserPrincipal;
import com.bside.study.user.entity.User;
import com.bside.study.user.dto.UserInfoResponse;
import com.bside.study.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.bside.study.common.api.ApiUtils.success;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UserController {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    @GetMapping("/users/me")
    public ApiResult<UserInfoResponse> getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        User user = userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));

        return success(modelMapper.map(user, UserInfoResponse.class));
    }
}
