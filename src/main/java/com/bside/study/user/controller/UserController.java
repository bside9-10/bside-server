package com.bside.study.user.controller;

import com.bside.study.common.api.ApiResult;
import com.bside.study.errors.ResourceNotFoundException;
import com.bside.study.security.CurrentUser;
import com.bside.study.security.UserPrincipal;
import com.bside.study.user.entity.User;
import com.bside.study.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.bside.study.common.api.ApiUtils.success;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    @GetMapping("/user/me")
    public ApiResult<User> getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        return success(userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId())));
    }
}
