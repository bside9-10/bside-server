package com.bside.study.goal.entity;

import com.bside.study.common.entity.LocalDateTimeEntity;
import com.bside.study.user.entity.User;
import lombok.*;

import javax.persistence.*;

@Getter
@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class GoalAvailableTime extends LocalDateTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "goal_available_time_id")
    private Long id;

    @Column(nullable = false)
    private int daily;

    @Column(nullable = false)
    private int weekly;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
