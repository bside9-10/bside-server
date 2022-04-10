package com.bside.study.goal.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class SaveGoalAvailableTimeRequestDto {

    @NotNull(message = "평일 하루 실천 가능 시간을 입력하세요")
    private int daily;

    @NotNull(message = "주말 하루 실천 가능 시간을 입력하세요")
    private int weekly;
}
