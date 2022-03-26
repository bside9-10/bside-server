package com.bside.study.user.dto;

import com.bside.study.user.entity.Role;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class SignUpRequest {
    @NotBlank(message = "이름을 입력해주세요")
    private String name;

    @NotBlank(message = "이메일을 입력해주세요")
    @Email
    private String email;

    @NotBlank(message = "비밀번호를 입력해주세요")
    private String password;

    @NotNull(message = "권한을 입력해주세요")
    private Role role;
}
