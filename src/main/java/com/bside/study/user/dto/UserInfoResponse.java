package com.bside.study.user.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserInfoResponse {
    private Long id;
    private String name;
    private String email;
    private int totalAvailableTime;
    private int goalCount;
    private int joinPeriod;
    private int successGoalDetailCount;

    public void calcTotalGoalAvailableTime(int daily, int weekly) {
        totalAvailableTime = (daily * 5) + (weekly * 2);
    }

    public void setGoalCount(Long goalCount) {
        this.goalCount = Integer.parseInt(String.valueOf(goalCount));
    }

    public void calcJoinPeriod(LocalDateTime createdDate) {
        Period between = Period.between(createdDate.toLocalDate(), LocalDate.now());
        this.joinPeriod = between.getDays() + 1;
    }

    public void successGoalDetailCount(Long count) {
        this.successGoalDetailCount = Integer.parseInt(String.valueOf(count));
    }
}
