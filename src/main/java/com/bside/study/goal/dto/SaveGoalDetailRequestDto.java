package com.bside.study.goal.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class SaveGoalDetailRequestDto {

    @NotNull(message = "목표 아이디를 입력하세요")
    private Long goalId;

    @NotNull(message = "세부 목표를 입력하세요")
    private String title;

    @NotNull(message = "목표 기간을 설정하세요")
    private LocalDate startDate;

    @NotNull(message = "목표 기간을 설정하세요")
    private LocalDate endDate;

    @NotNull(message = "시작 시간을 설정하세요")
    private String startTime;

    @NotNull(message = "종료 시간을 설정하세요")
    private String endTime;

    @NotNull(message = "알림여부를 설정하세요")
    private boolean notification;
}
