package com.bside.study.user.payload;

import com.bside.study.user.entity.AuthProvider;
import com.bside.study.user.entity.User;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class SignUpRequest {
    @NotBlank
    private String name;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;

    public User toEntity() {
        return User.builder()
                .name(name)
                .email(email)
                .password(password)
                .provider(AuthProvider.local)
                .build();
    }
}
