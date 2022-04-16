package com.bside.study.goal.entity;

import lombok.Getter;

@Getter
public enum GoalStatus {
    RUN("현재 진행 중인 목표 상태"),
    BEFORE("시작 전의 목표 상태"),
    AFTER("지난 목표 상태"),
    FULL("실천 가능시간 = 0"),
    OVERPLAN("실천가능시간 < 0");

    private final String desc;

    GoalStatus(String desc) {
        this.desc = desc;
    }
}

