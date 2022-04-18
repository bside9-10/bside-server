package com.bside.study.goal.entity;

import com.bside.study.common.entity.LocalDateTimeEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class GoalCalendar extends LocalDateTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "goal_calendar_id")
    private Long id;

    @Column(nullable = false)
    private LocalDate goalDate;

    private boolean completed;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "goal_detail_id")
    private GoalDetail goalDetail;

    public void checkCompleted() {
        this.completed = !completed;
    }

}
