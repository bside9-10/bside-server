package com.bside.study.goal.entity;

import com.bside.study.common.entity.LocalDateTimeEntity;
import lombok.*;

import javax.persistence.*;

@Getter
@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class GoalCategory extends LocalDateTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "goal_category_id")
    private Long id;

    private String goalCategoryName;

}
