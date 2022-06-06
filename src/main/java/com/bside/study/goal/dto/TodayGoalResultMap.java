package com.bside.study.goal.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

import java.util.List;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TodayGoalResultMap {
    private boolean flag;
    private List<TodayGoalResponseDto> data;

    public TodayGoalResultMap(boolean flag, List<TodayGoalResponseDto> data) {
        this.flag = flag;
        this.data = data;
    }
}
