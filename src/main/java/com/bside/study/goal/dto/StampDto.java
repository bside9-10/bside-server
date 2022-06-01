package com.bside.study.goal.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StampDto {
    private LocalDate goalDate;
    private Long failCount;
    private Long successCount;
    private int rate;
    private boolean isSuccessAny;
    private boolean isSuccessAll;

    public StampDto(LocalDate goalDate, Long failCount, Long successCount) {
        this.goalDate = goalDate;
        this.failCount = failCount;
        this.successCount = successCount;
        this.rate = (int) Math.round(((double) successCount / (failCount + successCount)) * 100);
        this.isSuccessAny = successCount > 0;
        this.isSuccessAll = rate == 100;
    }
}
