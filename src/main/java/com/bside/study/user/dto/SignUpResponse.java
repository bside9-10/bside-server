package com.bside.study.user.dto;

import lombok.Data;

@Data
public class SignUpResponse {
    private Long id;
    private String name;
    private String email;
}
