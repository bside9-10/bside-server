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
public class Goal extends LocalDateTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "goal_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "goal_category_id")
    private GoalCategory goalCategory;
}
