package com.bside.study.user.entity;

import com.bside.study.common.entity.LocalDateTimeEntity;
import com.bside.study.goal.entity.Goal;
import com.bside.study.goal.entity.GoalAvailableTime;
import com.bside.study.goal.entity.GoalDetail;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class User extends LocalDateTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private AuthProvider provider;

    private String providerId;

    @OneToMany(mappedBy = "user")
    private List<Goal> goals;

    @OneToMany(mappedBy = "user")
    private List<GoalDetail> goalDetails;

    @OneToMany(mappedBy = "user")
    private List<GoalAvailableTime> goalAvailableTimes;

    public void encodePassword(String encodePassword) {
        this.password = encodePassword;
    }

    public void localProvider() {
        this.provider = AuthProvider.local;
    }

}
