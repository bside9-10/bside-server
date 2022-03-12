package com.bside.study.user.controller;

import com.bside.study.user.dto.SignupRequestDto;
import com.bside.study.user.dto.SignupResponseDto;
import com.bside.study.user.service.UserService;
import com.bside.study.common.api.ApiResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.bside.study.common.api.ApiUtils.success;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ApiResult<SignupResponseDto> signup(@Valid @RequestBody SignupRequestDto signupRequestDto) {
        return success(userService.signup(signupRequestDto));
    }

}
