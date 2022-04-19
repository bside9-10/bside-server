package com.bside.study.goal.entity;

import lombok.Getter;

@Getter
public enum GoalDateStatus {
    DAILY("매일"),
    DAY("평일"),
    WEEKEND("주말");

    private final String desc;

    GoalDateStatus(String desc) {
        this.desc = desc;
    }
}