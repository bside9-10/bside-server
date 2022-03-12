package com.bside.study.user.service;

import com.bside.study.user.dto.SignupRequestDto;
import com.bside.study.user.dto.SignupResponseDto;
import com.bside.study.user.entity.User;
import com.bside.study.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public SignupResponseDto signup(SignupRequestDto signupRequestDto) {
        Optional<User> findUser = userRepository.findByName(signupRequestDto.getName());
        if (findUser.isPresent()) {
            throw new IllegalArgumentException("사용자 이름이 중복되었습니다");
        }

        User user = signupRequestDto.toEntity();
        user.setEncryptedPassword(passwordEncoder.encode(signupRequestDto.getPassword()));

        return new SignupResponseDto(userRepository.save(user));
    }
}
