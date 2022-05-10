package com.bside.study.goal.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

import java.time.DayOfWeek;
import java.time.LocalDate;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LafflesResponseDto {

    private Long goalDetailId;
    private String title;
    private LocalDate startDate;
    private LocalDate endDate;
    private String period;
    private int completedCount;
    private int failCount;
    private Long successRate;

    @QueryProjection
    public LafflesResponseDto(Long goalDetailId, String title, LocalDate startDate, LocalDate endDate, Long completedCount, Long failCount) {
        this.goalDetailId = goalDetailId;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.period = getPeriodString(startDate, endDate);
        this.completedCount = Integer.parseInt(String.valueOf(completedCount));
        this.failCount = Integer.parseInt(String.valueOf(failCount));
        this.successRate = Math.round(completedCount / (double) (completedCount + failCount) * 100);
    }

    private String getPeriodString(LocalDate startDate, LocalDate endDate) {
        StringBuilder sb = new StringBuilder();
        sb.append(startDate);
        appendDay(startDate.getDayOfWeek(), sb);

        sb.append(" ~ ");

        sb.append(endDate);
        appendDay(endDate.getDayOfWeek(), sb);
        return sb.toString();
    }

    private void appendDay(DayOfWeek dayOfWeek, StringBuilder sb) {
        if (DayOfWeek.MONDAY.equals(dayOfWeek)) {
            sb.append("(월)");
        } else if (DayOfWeek.TUESDAY.equals(dayOfWeek)) {
            sb.append("(화)");
        } else if (DayOfWeek.WEDNESDAY.equals(dayOfWeek)) {
            sb.append("(수)");
        } else if (DayOfWeek.THURSDAY.equals(dayOfWeek)) {
            sb.append("(목)");
        } else if (DayOfWeek.FRIDAY.equals(dayOfWeek)) {
            sb.append("(금)");
        } else if (DayOfWeek.SATURDAY.equals(dayOfWeek)) {
            sb.append("(토)");
        } else {
            sb.append("(일)");
        }
    }
}
