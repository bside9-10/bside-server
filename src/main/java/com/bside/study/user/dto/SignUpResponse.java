package com.bside.study.user.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SignUpResponse {
    private Long id;
    private String name;
    private String email;
}
