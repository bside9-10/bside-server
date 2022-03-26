package com.bside.study.user.controller;

import com.bside.study.common.api.ApiResult;
import com.bside.study.errors.BadRequestException;
import com.bside.study.security.TokenProvider;
import com.bside.study.user.entity.User;
import com.bside.study.user.dto.AuthResponse;
import com.bside.study.user.dto.LoginRequest;
import com.bside.study.user.dto.SignUpRequest;
import com.bside.study.user.dto.SignUpResponse;
import com.bside.study.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.bside.study.common.api.ApiUtils.success;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final ModelMapper modelMapper;

    @PostMapping("/login")
    public ApiResult<AuthResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = tokenProvider.createToken(authentication);
        return success(new AuthResponse(token));
    }

    @PostMapping("/signup")
    public ApiResult<SignUpResponse> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        if(userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new BadRequestException("이미 사용중인 이메일입니다.");
        }

        User user = modelMapper.map(signUpRequest, User.class);
        user.encodePassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user.localProvider();

        userRepository.save(user);

        return success(modelMapper.map(user, SignUpResponse.class));
    }

}
