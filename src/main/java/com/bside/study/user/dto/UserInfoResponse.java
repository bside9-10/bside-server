package com.bside.study.user.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserInfoResponse {
    private Long id;
    private String name;
    private String email;
    private int total;
    private int goalCount;

    public void calcTotalGoalAvailableTime(int daily, int weekly) {
        total = (daily * 5) + (weekly * 2);
    }

    public void setGoalCount(Long goalCount) {
        this.goalCount = Integer.parseInt(String.valueOf(goalCount));
    }
}
